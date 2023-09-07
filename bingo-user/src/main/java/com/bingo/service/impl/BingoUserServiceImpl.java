package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoUserMapper;
import com.bingo.pojo.dto.user.UserDTO;
import com.bingo.pojo.po.user.BingoUser;
import com.bingo.pojo.resp.user.UserResp;
import com.bingo.service.BingoUserService;
import com.bingo.store.BingoUserStore;
import com.bingo.utils.*;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
@Service
public class BingoUserServiceImpl extends ServiceImpl<BingoUserMapper, BingoUser> implements BingoUserService {
    @Autowired
    private BingoUserStore userStore;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private JavaMailSender mailSender;
    private static final String captchaKey = "bingo_captcha";

    /**
     * 根据Id查询用户信息
     */
    @Override
    public UserResp findById(Long uid) {
        UserResp userResp = new UserResp();
        BingoUser userInfo = userStore.findById(uid);
        if (ObjectUtils.isNotEmpty(userInfo)) {
            BeanUtils.copyProperties(userInfo, userResp);
        }
        return userResp;
    }

    /**
     * 生成验证码图片
     * ---------------------------------------------
     * Cookie结构：K("captcha_key") V(客户端身份uuid)
     * Redis结构：K(客户端身份uuid) V(验证码值)
     * ---------------------------------------------
     * 1.生成验证码同时，将客户端身份uuid传到请求中
     * 2.将验证码数据存入到Redis中
     * ---------------------------------------------
     */
    @Override
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 生成验证码图片、内容
        String verifyCode = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(verifyCode);

        // Cookie没有CaptchaKey，赋值Cookie和Redis
        if (!CookieUtil.hasCookie(request, captchaKey)) {
            String uuid = UUID.randomUUID().toString();
            CookieUtil.addCookie(response, captchaKey, uuid, false, -1, "/");
            redisTemplate.opsForValue().set(uuid, verifyCode, 3, TimeUnit.MINUTES);
        }

        // Cookie有CaptchaKey，重新赋值Redis
        else {
            String uuid = CookieUtil.getValue(request, captchaKey);
            redisTemplate.opsForValue().set(uuid, verifyCode, 3, TimeUnit.MINUTES);
        }

        // 将图片输出到页面
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 修改用户信息
     */
    @Override
    public Boolean updateUserById(BingoUser user) {
        return userStore.updateUserById(user);
    }

    /**
     * 根据ids批量查询用户信息
     */
    @Override
    public List<UserResp> getUserByIds(List<Long> ids) {
        List<BingoUser> userInfoList = userStore.getUserListByIds(ids);
        List<UserResp> userRespList = new ArrayList<>();
        for (BingoUser bingoUser : userInfoList) {
            UserResp userResp = new UserResp();
            BeanUtils.copyProperties(bingoUser, userResp);
            userRespList.add(userResp);
        }
        return userRespList;
    }

    /**
     * 注册
     */
    @Override
    public Boolean register(UserDTO userDTO, HttpServletRequest request) throws Exception {
        String accountId = userDTO.getAccountId();
        String passWord = userDTO.getPassWord();
        String email = userDTO.getEmail();
        String captcha = userDTO.getCaptcha();

        // 邮箱验证码是否过期
        String code = (String) redisTemplate.opsForValue().get(email);
        if (StringUtils.isEmpty(code)) {
            throw new Exception("验证码已失效，请重新发送");
        }

        // 验证码输入是否正确
        if (!captcha.equalsIgnoreCase(code)) {
            throw new Exception("您输入的验证码有误，请重试");
        }

        // 判断账号是否注册过
        BingoUser userInfo = userStore.findByAccountId(accountId);
        if (ObjectUtils.isNotEmpty(userInfo)) {
            throw new Exception("该账号已存在，换个试试~");
        }

        // 解析用户所在区域
        String ipAddress = IPUtil.getIpAddress(request);
        // FIXME 目前测试，这里先写死，本地ip校验必报错
        Map<String, String> regionMap = IPUtil.getCityInfo("220.181.38.150");
        String province = regionMap.get("province");
        String city = regionMap.get("city");

        // TODO 保存用户统计数据信息

        // 保存账号信息
        BingoUser user = new BingoUser();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassWord(AESUtil.encrypt(passWord));
        user.setLocation(province + "-" + city);
        return userStore.save(user);
    }

    /**
     * 登录功能
     * 1.登录成功后，将token信息保存到客户端的localStorage（使用Cookie也可以）
     * 2.请求拦截器：将localStorage中的token信息通过存入到请求头中，每次后端请求都通过拦截器校验
     * 3.后端拦截器校验成功那就不做任何操作，校验Token失败就让前端退出登陆状态，返回到首页
     */
    @Override
    public String login(UserDTO userDTO) throws Exception {
        String accountId = userDTO.getAccountId();
        String passWord = userDTO.getPassWord();
        String captcha = userDTO.getCaptcha();
        String captchaKey = userDTO.getCaptchaKey();

        // 判断验证码是否正确
        String captchaVal = (String) redisTemplate.opsForValue().get(captchaKey);
        if (StringUtils.isEmpty(captcha)) {
            throw new Exception("验证码生成异常，请重新刷新页面");
        }
        if (!captcha.equalsIgnoreCase(captchaVal)) {
            throw new Exception("验证码不正确，请重新输入");
        }

        // 判断用户账号是否存在
        BingoUser userInfo = userStore.findByAccountId(accountId);
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new Exception("该用户账号不存在，请重试");
        }

        // 判断密码是否正确
        String encryptPassWord = AESUtil.encrypt(passWord);
        String dbPassWord = userInfo.getPassWord();
        if (!encryptPassWord.equals(dbPassWord)) {
            throw new Exception("密码错误，请重试");
        }

        // 生成Token
        Long userId = userInfo.getId();
        String token = JWTUtil.generateToken(userId);
        if (StringUtils.isEmpty(token)) {
            throw new Exception("生成Token用户信息异常");
        }
        return token;
    }

    /**
     * 发送邮件
     */
    @Override
    public void sendEmail(String email) throws Exception {
        if (StringUtils.isEmpty(email)) {
            throw new Exception("验证码为空，请重试");
        }

        // 生成邮箱验证码
        String code = RandomUtil.generateCode(6);

        // 发送邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("1262254123@qq.com");
        mailMessage.setTo(email);
        mailMessage.setSubject("Bingo注册验证");
        mailMessage.setText("欢迎使用Bingo平台，您的验证码为：" + code + "，3分钟内自动过期~");
        mailMessage.setSentDate(new Date());
        mailSender.send(mailMessage);

        // 保存 Email 验证码（3分钟后自动过期）
        redisTemplate.opsForValue().set(email, code, 3, TimeUnit.MINUTES);
    }

    /**
     * 解析Token获取用户信息
     */
    @Override
    public BingoUser resolveToken(String token) throws Exception {
        Long userId = JWTUtil.resolveTokenToUid(token);
        BingoUser userInfo = userStore.findById(userId);
        if (ObjectUtils.isEmpty(userInfo)) {
            throw new Exception("没有查询到该uid的用户信息");
        }
        return userInfo;
    }

    /**
     * 修改用户在线状态（针对IM聊天）
     */
    @Override
    public Boolean updateOnlineStatus(Long uid, Integer status) {
        BingoUser user = new BingoUser();
        userStore.updateUserById(user);
        return null;
    }
}

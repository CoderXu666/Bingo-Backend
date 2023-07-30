package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoUserMapper;
import com.bingo.pojo.dto.UserDTO;
import com.bingo.pojo.po.BingoUser;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.service.BingoUserService;
import com.bingo.store.BingoUserStore;
import com.bingo.utils.AESUtil;
import com.bingo.utils.JWTUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 根据Id查询用户信息
     */
    @Override
    public BingoUserVO findById(Long id) {
        BingoUserVO userVO = new BingoUserVO();
        BingoUser userInfo = userStore.findById(id);
//        BingoUserStatistics userStatistics = userStatisticsStore.findUserSta(id);
        if (ObjectUtils.isNotEmpty(userInfo)) {
            BeanUtils.copyProperties(userInfo, userVO);
        }
//        if (ObjectUtils.isNotEmpty(userStatistics)) {
//            BeanUtils.copyProperties(userStatistics, userVO);
//        }
        return userVO;
    }

    /**
     * 获取验证码图片
     */
    @Override
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        // 定义response输出类型为image/jpeg
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        // 生成验证码图片
        String verifyCode = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(verifyCode);

//        // Cookie不存在，那就重新生成Cookie，赋值Redis
//        String captchaKey = "captcha_key";
//        Cookie[] cookies = request.getCookies();
//        if (ObjectUtils.isEmpty(cookies)) {
//            String uuid = UUID.randomUUID().toString();
//            Cookie cookie = new Cookie(captchaKey, uuid);
//            cookie.setPath("/");
//            response.addCookie(cookie);
//            redisTemplate.opsForValue().set(uuid, verifyCode, 3, TimeUnit.MINUTES);
//        }
//
//        // Cookie存在，不生成Cookie，重新生成验证码赋值Redis即可
//        else {
//            boolean hasCaptchaKeyFlag = false;
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals(captchaKey)) {
//                    redisTemplate.opsForValue().set(cookie.getValue(), verifyCode, 3, TimeUnit.MINUTES);
//                    hasCaptchaKeyFlag = true;
//                }
//            }
//            // 特殊情况，偶尔复现
//            if (!hasCaptchaKeyFlag) {
//                String uuid = UUID.randomUUID().toString();
//                Cookie cookie = new Cookie(captchaKey, uuid);
//                cookie.setPath("/");
//                response.addCookie(cookie);
//                redisTemplate.opsForValue().set(uuid, verifyCode, 3, TimeUnit.MINUTES);
//            }
//        }

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
    public Boolean updateUser(BingoUser user) {
        return userStore.updateUser(user);
    }

    /**
     * 根据ids批量查询用户信息
     */
    @Override
    public List<BingoUserVO> getUserByIds(List<Long> ids) {
        List<BingoUser> userInfoList = userStore.getUserListByIds(ids);
        List<BingoUserVO> userVOList = new ArrayList<>();
        for (BingoUser bingoUser : userInfoList) {
            BingoUserVO userVO = new BingoUserVO();
            BeanUtils.copyProperties(bingoUser, userVO);
            userVOList.add(userVO);
        }
        return userVOList;
    }

    /**
     * 注册
     */
    @Override
    public Boolean register(UserDTO userDTO) throws Exception {
        String accountId = userDTO.getAccountId();
        String passWord = userDTO.getPassWord();
        String captcha = userDTO.getCaptcha();

        // TODO: 入参校验：JSR 303

        // 判断账号是否注册过
        BingoUser userInfo = userStore.findByAccountId(accountId);
        if (ObjectUtils.isNotEmpty(userInfo)) {
            throw new Exception("该账号已存在，换个试试~");
        }

        // 校验验证码
        String redisCaptcha = (String) redisTemplate.opsForValue().get("");
        if (!captcha.equalsIgnoreCase(redisCaptcha)) {
            throw new Exception("验证码错误，请重试");
        }

        // 保存账号信息
        BingoUser user = new BingoUser();
        BeanUtils.copyProperties(userDTO, user);
        user.setPassWord(AESUtil.encrypt(passWord));
        return userStore.save(user);
    }

    /**
     * 登录
     */
    @Override
    public String login(UserDTO userDTO) throws Exception {
        String accountId = userDTO.getAccountId();
        String passWord = userDTO.getPassWord();

        // TODO JSR 303检验

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
        return JWTUtil.generateToken(accountId);
    }
}

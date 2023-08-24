package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.user.UserDTO;
import com.bingo.pojo.po.user.BingoUser;
import com.bingo.pojo.resp.R;
import com.bingo.pojo.vo.user.UserVO;
import com.bingo.service.BingoUserService;
import com.bingo.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
@RestController
public class BingoUserController {
    @Autowired
    private BingoUserService userService;
    @Autowired
    private MinioUtil minioUtil;

    /**
     * 根据userId查询用户信息
     */
    @GetMapping("/find_by_id")
    public R findByUserId(Long id) {
        UserVO userVO = userService.findById(id);
        return R.out(RespCodeEnum.SUCCESS, userVO);
    }

    /**
     * 注册功能
     */
    @PostMapping("/register")
    public R register(@RequestBody UserDTO userDTO) throws Exception {
        userService.register(userDTO);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
    }

    /**
     * 登录功能
     * 1.登录成功后，将token信息保存到客户端的localStorage（使用Cookie也可以）
     * 2.将token信息通过请求拦截器存入到请求头中，每次发起后端请求都通过后端拦截器校验
     */
    @PostMapping("/login")
    public R login(@RequestBody UserDTO userDTO) throws Exception {
        String token = userService.login(userDTO);
        return R.out(RespCodeEnum.SUCCESS, token);
    }

    /**
     * 解析Token拿到用户信息
     */
    @GetMapping("/resolve_token")
    public R resolveToken(String token) throws Exception {
        BingoUser userInfo = userService.resolveToken(token);
        return R.out(RespCodeEnum.SUCCESS, userInfo);
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/update")
    public R updateUserInfo(@RequestBody BingoUser user) {
        userService.updateUser(user);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
    }

    /**
     * 获取验证码图片
     */
    @GetMapping("/captcha")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        userService.generateCode(request, response);
    }

    /**
     * 发送邮件
     */
    @PostMapping("/send_email")
    public R sendEmail(String email) throws Exception {
        userService.sendEmail(email);
        return R.out(RespCodeEnum.SUCCESS, "邮件发送成功");
    }

    /**
     * 根据ids批量查询用户信息
     * postman前端传参数不要声明变量名，否则调用不通，例如：[1,2,3]
     */
    @PostMapping("/list_by_ids")
    public R getUserInfoByIds(List<Long> ids) {
        List<UserVO> userList = userService.getUserByIds(ids);
        return R.out(RespCodeEnum.SUCCESS, userList);
    }

    /**
     * 上传头像
     * <p>
     * 访问URL：http://101.42.13.186:9000/avatar-bucket/1687483809516_1690621557315.jpg
     */
    @PostMapping("/upload_avatar")
    public R uploadAvatar(MultipartFile file) {
        String avatarUrl = minioUtil.upload(file, "avatar-bucket");
        return R.out(RespCodeEnum.SUCCESS, avatarUrl);
    }

    /**
     * 移除头像
     */
    @DeleteMapping("/remove_avatar")
    public R removeAvatar(String objectName) throws Exception {
        minioUtil.removeObject("avatar-bucket", objectName);
        return R.out(RespCodeEnum.SUCCESS, "移除成功");
    }


    // 测试专用
    @GetMapping("/test")
    public void getInfo() {

    }
}


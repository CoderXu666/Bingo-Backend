package com.bingo.controller;


import com.bingo.annotation.RateLimiter;
import com.bingo.enums.ResponseEnum;
import com.bingo.pojo.dto.user.UserDTO;
import com.bingo.pojo.po.user.BingoUser;
import com.bingo.pojo.resp.user.UserResp;
import com.bingo.response.R;
import com.bingo.service.BingoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
@Slf4j
@RestController
public class BingoUserController {
    @Autowired
    private BingoUserService userService;

    /**
     * 根据userId查询用户信息
     */
    @GetMapping("/find_by_id")
    public R findByUserId(Long uid) throws Exception {
        UserResp userResp = userService.findById(uid);
        return R.out(ResponseEnum.SUCCESS, userResp);
    }

    /**
     * 注册功能
     */
    @PostMapping("/register")
    public R register(@RequestBody UserDTO userDTO, HttpServletRequest request) throws Exception {
        userService.register(userDTO, request);
        return R.out(ResponseEnum.SUCCESS, ResponseEnum.SUCCESS.getMsg());
    }

    /**
     * 登录功能
     * -----------------------------------------------------------------------------
     * 通用方式：
     * 1.登录成功后，将token信息保存到客户端的localStorage（使用Cookie也可以）
     * 2.请求拦截器：将localStorage中的token信息通过存入到请求头中，每次后端请求都通过拦截器校验
     * 3.后端拦截器校验成功那就不做任何操作，校验Token失败就让前端退出登陆状态，返回到首页
     */
    @PostMapping("/login")
    public R login(@RequestBody UserDTO userDTO) throws Exception {
        String token = userService.login(userDTO);
        return R.out(ResponseEnum.SUCCESS, token);
    }

    /**
     * 解析 Token
     */
    @GetMapping("/resolve_token")
    public R resolveToken(String token) throws Exception {
        BingoUser userInfo = userService.resolveToken(token);
        return R.out(ResponseEnum.SUCCESS, userInfo);
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/update")
    public R updateUserById(@RequestBody BingoUser user) throws Exception {
        userService.updateUserById(user);
        return R.out(ResponseEnum.SUCCESS, null);
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
        return R.out(ResponseEnum.SUCCESS, null);
    }

    /**
     * 根据ids批量查询用户信息
     * ---------------------------------------------------------------
     * PostMan使用JSON格式传递，不要声名变量名，格式如下：
     * [
     * 1695363601690537985,
     * 1695363601690537986
     * ]
     */
    @PostMapping("/list_by_ids")
    public R getUserInfoByIds(@RequestBody List<Long> ids) {
        List<UserResp> userList = userService.getUserByIds(ids);
        return R.out(ResponseEnum.SUCCESS, userList);
    }

    /**
     * 修改用户在线状态（针对IM聊天）
     */
    @PostMapping("/update_online")
    public R updateOnline(Long uid, Integer status) throws Exception {
        userService.updateOnlineStatus(uid, status);
        return R.out(ResponseEnum.SUCCESS, null);
    }


    /**
     * Test测试专用接口
     */
    @GetMapping("/test")
    @RateLimiter(time = 3, count = 2)
    public String getInfo() throws InterruptedException {
        return "接口调用成功";
    }
}


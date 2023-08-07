package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.EmailDTO;
import com.bingo.pojo.dto.UserDTO;
import com.bingo.pojo.po.BingoUser;
import com.bingo.pojo.resp.R;
import com.bingo.pojo.vo.BingoUserVO;
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
@RequestMapping("/customer")
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
        BingoUserVO userVO = userService.findById(id);
        return R.out(RespCodeEnum.SUCCESS, userVO);
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public R register(@RequestBody UserDTO userDTO) throws Exception {
        userService.register(userDTO);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public R login(@RequestBody UserDTO userDTO) throws Exception {
        userService.login(userDTO);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
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
    public R sendEmail(@RequestBody EmailDTO emailDTO) {
        userService.sendEmail(emailDTO);
        return R.out(RespCodeEnum.SUCCESS, "邮件发送成功");
    }

    /**
     * 根据ids批量查询用户信息
     * postman前端传参数不要声明变量名，否则调用不通，例如：[1,2,3]
     */
    @PostMapping("/get_list")
    public R getUserInfoByIds(List<Long> ids) {
        List<BingoUserVO> userList = userService.getUserByIds(ids);
        return R.out(RespCodeEnum.SUCCESS, userList);
    }

    /**
     * 上传头像
     * <p>
     * 访问URL：http://101.42.13.186:9000/avatar-bucket/1687483809516_1690621557315.jpg
     */
    @PostMapping("/upload_avatar")
    public R uploadAvatar(MultipartFile file, UserDTO userDTO) {
        String avatarUrl = minioUtil.upload(file, "avatar-bucket");
        return R.out(RespCodeEnum.SUCCESS, avatarUrl);
    }


    // 测试专用
    @GetMapping("/test")
    public void getInfo() {

    }
}


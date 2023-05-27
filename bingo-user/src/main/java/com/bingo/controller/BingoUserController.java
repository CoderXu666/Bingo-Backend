package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoUser;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.resp.R;
import com.bingo.service.BingoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/user")
public class BingoUserController {
    @Autowired
    private BingoUserService userService;

    /**
     * 根据userId查询用户信息
     */
    @GetMapping("/find_by_id")
    public R findByUserId(Long id) {
        try {
            BingoUserVO userVO = userService.findById(id);
            return R.out(RespCodeEnum.SUCCESS, userVO);
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

    /**
     * 注册
     */
    @PostMapping("/register")
    public R register(@RequestBody BingoUser user) {
        try {
            return null;
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public R login(@RequestBody BingoUser user) {
        try {
            return null;
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

    /**
     * 修改用户信息
     */
    @PostMapping("/update")
    public R updateUserInfo(@RequestBody BingoUser user) {
        try {
            userService.updateUser(user);
            return R.out(RespCodeEnum.SUCCESS, "操作成功");
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

    /**
     * 获取验证码图片
     */
    @GetMapping("/captcha")
    public void generateCode(HttpServletRequest request, HttpServletResponse response) {
        userService.generateCode(request, response);
    }

    /**
     * 根据ids批量查询用户信息
     */
    @GetMapping("/get_list")
    public R getUserInfoByIds(List<Long> ids) {
        try {
            List<BingoUserVO> userList = userService.getUserByIds(ids);
            return R.out(RespCodeEnum.SUCCESS, "userList");
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }
}


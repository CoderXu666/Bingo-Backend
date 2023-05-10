package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoUser;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.resp.R;
import com.bingo.service.BingoUserService;
import com.bingo.store.BingoUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    private BingoUserService bingoUserService;
    @Autowired
    private BingoUserStore bingoUserStore;

    /**
     * 根据userName查询用户信息
     */
    @GetMapping("find_by_username")
    public R findUserInfo(String userName) {
        try {
            BingoUserVO bingoUserVO = bingoUserService.findUserInfo(userName);
            return R.out(RespCodeEnum.SUCCESS, bingoUserVO);
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

    /**
     * 根据userName查询用户信息
     */
    @PostMapping("update")
    public R updateUserInfo(@RequestBody BingoUser user) {
        try {
            bingoUserStore.updateUser(user);
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
        bingoUserStore.generateCode(request, response);
    }
}


package com.bingo.controller;


import com.bingo.pojo.po.BingoUser;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.resp.R;
import com.bingo.service.BingoUserService;
import com.bingo.store.BingoUserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param userName
     * @return
     */
    @GetMapping("find_by_username")
    public R findUserInfo(String userName) {
        try {
            BingoUserVO bingoUserVO = bingoUserService.findUserInfo(userName);
            return R.succeed(bingoUserVO, "操作成功");
        } catch (Exception e) {
            return R.failed("", "操作失败");
        }
    }

    /**
     * 根据userName查询用户信息
     *
     * @param
     * @return
     */
    @PostMapping("update")
    public R updateUserInfo(@RequestBody BingoUser user) {
        try {
            bingoUserStore.updateUser(user);
            return R.succeed("bingoUserVO", "操作成功");
        } catch (Exception e) {
            return R.failed("", "操作失败");
        }
    }
}


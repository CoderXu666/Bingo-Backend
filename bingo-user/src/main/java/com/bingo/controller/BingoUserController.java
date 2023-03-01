package com.bingo.controller;


import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.resp.R;
import com.bingo.service.BingoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}


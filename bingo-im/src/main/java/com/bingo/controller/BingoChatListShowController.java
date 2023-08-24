package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.resp.R;
import com.bingo.pojo.vo.user.BingoUserVO;
import com.bingo.service.BingoChatListShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 聊天窗口列表（展示） 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
@RestController
@RequestMapping("/show")
public class BingoChatListShowController {
    @Autowired
    private BingoChatListShowService showService;

    /**
     * 查询聊天列表
     */
    @GetMapping("/list/{userId}")
    public R getChatList(@PathVariable("userId") String userId) {
        List<BingoUserVO> list = showService.getChatList(userId);
        return R.out(RespCodeEnum.SUCCESS, list);
    }
}


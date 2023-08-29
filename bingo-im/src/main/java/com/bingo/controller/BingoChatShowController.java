package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoChatShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 聊天窗口列表（展示） 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
@RestController
@RequestMapping("/chat")
public class BingoChatShowController {
    @Autowired
    private BingoChatShowService showService;

    /**
     * 查询聊天列表
     */
    @GetMapping("/list/{uid}")
    public R getChatList(@PathVariable("uid") Long userId) {
        Map<Object, Object> resultMap = showService.getChatList(userId);
        return R.out(RespCodeEnum.SUCCESS, resultMap);
    }
}


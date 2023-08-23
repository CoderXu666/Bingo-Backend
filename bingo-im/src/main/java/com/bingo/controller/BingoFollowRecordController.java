package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoFollowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 用户关注表 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
@RestController
@RequestMapping("/bingoFollowRecord")
public class BingoFollowRecordController {
    @Autowired
    private BingoFollowRecordService followService;

    /**
     * 查询聊天好友（包含聊天记录）
     */
    @GetMapping("/friend_list")
    public R list(Long userId) {
        Map<String, Object> map = followService.getChatList(userId);
        return R.out(RespCodeEnum.SUCCESS, map);
    }
}


package com.bingo.controller;

import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoFollowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-08-25  16:05
 * @Description: 用户关注
 * @Version: 1.0
 */
@RestController
@RequestMapping("/follow")
public class BingoFollowRecordController {
    @Autowired
    private BingoFollowRecordService followRecordService;

    /**
     * 关注用户/取消关注
     */
    @PostMapping("/save_or_cancel")
    public R followUser(Long userId, Long goalId) {
        followRecordService.followUser(userId, goalId);
        return R.out(RespCodeEnum.SUCCESS, "关注成功");
    }

    /**
     * 关注列表
     */
    @GetMapping("/follow_list")
    public R followList(Long userId, Integer current, Integer limit) {
        Map<String, Object> resultMap = followRecordService.followList(userId, current, limit);
        return R.out(RespCodeEnum.SUCCESS, resultMap);
    }

    /**
     * 粉丝列表
     */
    @GetMapping("fan_list/{goalId}/{current}/{size}")
    public R fanList(@PathVariable("goalId") Long goalId, Integer current, Integer limit) {
        Map<String, Object> resultMap = followRecordService.fanList(goalId, current, limit);
        return R.out(RespCodeEnum.SUCCESS, resultMap);
    }

    /**
     * 互关列表（好友）
     */
    @GetMapping("/friend_list/{userId}/{goalId}/{current}/{size}")
    public R friendList(Long userId, Long goalId, Integer current, Integer limit) {
        Map<String, Object> resultMap = followRecordService.friendList(userId, goalId, current, limit);
        return R.out(RespCodeEnum.SUCCESS, resultMap);
    }
}

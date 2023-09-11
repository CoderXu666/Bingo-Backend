package com.bingo.controller;

import com.bingo.enums.RespCodeEnum;
import com.bingo.response.R;
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
    @PostMapping("/save")
    public R followUser(Long uid, Long goalId) throws Exception {
        followRecordService.followUser(uid, goalId);
        return R.out(RespCodeEnum.SUCCESS, null);
    }

    /**
     * 关注列表
     */
    @GetMapping("/list")
    public R followList(Long uid, Integer current, Integer limit) {
        Map<String, Object> resultMap = followRecordService.followList(uid, current, limit);
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
    @GetMapping("/friend_list/{uid}/{goalId}/{current}/{size}")
    public R friendList(Long uid, Long goalId, Integer current, Integer limit) {
        Map<String, Object> resultMap = followRecordService.friendList(uid, goalId, current, limit);
        return R.out(RespCodeEnum.SUCCESS, resultMap);
    }
}

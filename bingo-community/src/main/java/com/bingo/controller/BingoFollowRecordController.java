package com.bingo.controller;

import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoFollowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-08-25  16:05
 * @Description: TODO
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
}

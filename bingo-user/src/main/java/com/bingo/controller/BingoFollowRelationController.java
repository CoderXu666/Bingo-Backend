package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoFollowRelation;
import com.bingo.pojo.resp.R;
import com.bingo.pojo.vo.BingoFollowVO;
import com.bingo.service.BingoFollowRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-09
 */
@RestController
@RequestMapping("/follow")
public class BingoFollowRelationController {
    @Autowired
    private BingoFollowRelationService followRelationService;

    /**
     * 关注/取消用户
     */
    @PostMapping("/follow_user")
    public R followUser(Long userId1, Long userId2) {
        followRelationService.followUser(userId1, userId2);
        return R.out(RespCodeEnum.SUCCESS, "关注成功");
    }

    /**
     * 查询用户的关注列表
     */
    @GetMapping("/follow_list")
    public R findFollowList(Long userId) {
        List<BingoFollowVO> followRelation = followRelationService.findFollowList(userId);
        return R.out(RespCodeEnum.SUCCESS, followRelation);
    }
}


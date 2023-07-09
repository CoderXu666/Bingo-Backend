package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoFollowRelation;
import com.bingo.pojo.resp.R;
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
 * @author 周英俊
 * @since 2023-07-09
 */
@RestController
@RequestMapping("/follow")
public class BingoFollowRelationController {
    @Autowired
    private BingoFollowRelationService followRelationService;

    /**
     * 关注用户
     */
    @PostMapping("/save_follow")
    public R saveFollow(Long userId1, Long userId2) throws Exception {
        followRelationService.saveFollow(userId1, userId2);
        return R.out(RespCodeEnum.SUCCESS, "关注成功");
    }

    /**
     * 查询用户的关注
     */
    @GetMapping("/find_follow")
    public R findFollow(Long userId1) {
        List<BingoFollowRelation> followRelation = followRelationService.findFollow(userId1);
        return R.out(RespCodeEnum.SUCCESS, followRelation);
    }

    /**
     * 取消关注
     */
    @GetMapping("")
    public R deleteFollow(Long userId1, Long userId2) {
        // followRelationService.deleteFollow(userId1, userId2);
        return R.out(RespCodeEnum.SUCCESS, "");


    }


}


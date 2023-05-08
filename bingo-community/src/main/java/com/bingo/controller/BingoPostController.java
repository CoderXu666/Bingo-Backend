package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoPost;
import com.bingo.resp.R;
import com.bingo.service.BingoPostService;
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
 * @since 2023-03-04
 */
@RestController
@RequestMapping("/bingoPost")
public class BingoPostController {
    @Autowired
    private BingoPostService postService;

    @PostMapping("/save")
    public R save() {
        BingoPost bingoPost = new BingoPost();
//        bingoPost.setId("fjoidsfjoisdfjosdoi");
        bingoPost.setPostFont("fjoidsjfdsifojs");
        bingoPost.setLikeCount(1111);
        bingoPost.setPostUrl("hdiofihodsfodsiofio");
        bingoPost.setUserName("徐志斌");
        boolean save = postService.saveInfo(bingoPost);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
    }

    @GetMapping("list")
    public R list() {
        List<BingoPost> list = postService.list();
        return R.out(RespCodeEnum.SUCCESS, list);
    }
}


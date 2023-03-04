package com.bingo.controller;


import com.bingo.pojo.po.BingoPost;
import com.bingo.resp.R;
import com.bingo.service.BingoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        bingoPost.setPostFont("fjoidsjfdsifojs");
        bingoPost.setLikeCount(1111);
        bingoPost.setPostUrl("hdiofihodsfodsiofio");
        bingoPost.setUserName("徐志斌");
        boolean save = postService.saveInfo(bingoPost);
        return R.succeed(null, "操作成功");
    }
}


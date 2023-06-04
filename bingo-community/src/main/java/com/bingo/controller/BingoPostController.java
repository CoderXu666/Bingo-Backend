package com.bingo.controller;

import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.LikeDTO;
import com.bingo.pojo.dto.PostDTO;
import com.bingo.pojo.vo.PostVO;
import com.bingo.resp.R;
import com.bingo.service.BingoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-13
 */
@RestController
@RequestMapping("/post")
public class BingoPostController {
    @Autowired
    private BingoPostService postService;

    /**
     * 发布帖子
     */
    @PostMapping("/save")
    public R savePost(@RequestBody PostDTO postDTO) {
        try {
            postService.savePost(postDTO);
            return R.out(RespCodeEnum.SUCCESS, "操作成功");
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

    /**
     * 根据关键字，搜索帖子
     */
    @GetMapping("/search")
    public R searchPost(String content) {
        try {
            List<PostVO> result = postService.searchPost(content);
            return R.out(RespCodeEnum.SUCCESS, result);
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

    /**
     * 点赞帖子
     */
    @PostMapping("/like")
    public R likePost(@RequestBody LikeDTO likeDTO) {
        try {
            postService.likePost(likeDTO);
            return R.out(RespCodeEnum.SUCCESS, "操作成功");
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }
}


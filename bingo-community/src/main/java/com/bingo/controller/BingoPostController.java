package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.BingoPostDTO;
import com.bingo.resp.R;
import com.bingo.service.BingoPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public R savePost(@RequestBody BingoPostDTO postDTO) {
        try {
            postService.savePost(postDTO);
            return R.out(RespCodeEnum.SUCCESS, null);
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }
}


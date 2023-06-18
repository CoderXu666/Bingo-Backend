package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoPostComment;
import com.bingo.resp.R;
import com.bingo.service.BingoPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-13
 */
@RestController
@RequestMapping("/comment")
public class BingoPostCommentController {
    @Autowired
    private BingoPostCommentService postCommentService;
    /**
     * 查询帖子信息
     */
    @GetMapping("/comment_list")
    public R commentList(Long commentId) {
        try {
            List<BingoPostComment> list = postCommentService.searchPostCommentList(commentId);
            return R.out(RespCodeEnum.SUCCESS, list);
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }

    /**
     * 对帖子进行评论
     */

    /**
     * 删除帖子评论
     */
}


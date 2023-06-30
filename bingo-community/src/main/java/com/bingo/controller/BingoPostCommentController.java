package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoPostComment;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoPostCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
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
        List<BingoPostComment> list = postCommentService.searchPostCommentList(commentId);
        return R.out(RespCodeEnum.SUCCESS, list);
    }

    /**
     * 对帖子进行评论
     */
    @PostMapping("/do_comment")
    public R doComment(Long postId, Long userId) {
//        Boolean isSuccess = postCommentService.doComment();
        return R.out(RespCodeEnum.SUCCESS, null);
    }

    /**
     * 删除帖子评论
     */
    @DeleteMapping("/delete_comment")
    public R deleteComment(Long commentId) {
        Boolean isSuccess = postCommentService.deleteCommentById(commentId);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
    }
}


package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoPostCommentMapper;
import com.bingo.pojo.po.BingoPostComment;
import com.bingo.service.BingoPostCommentService;
import com.bingo.store.BingoPostCommentStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-13
 */
@Service
public class BingoPostCommentServiceImpl extends ServiceImpl<BingoPostCommentMapper, BingoPostComment> implements BingoPostCommentService {
    @Autowired
    private BingoPostCommentStore postCommentStore;

    @Override
    public List<BingoPostComment> searchPostCommentList(Long commentId) {
        List<BingoPostComment> list = postCommentStore.searchPostCommentList(commentId);
        return list;
    }

    @Override
    public Boolean deleteCommentById(Long commentId) {
        return postCommentStore.deleteCommentById(commentId);
    }
}

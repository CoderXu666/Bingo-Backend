package com.bingo.store;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-13
 */
public interface BingoPostCommentStore extends IService<BingoPostComment> {
    List<BingoPostComment> searchPostCommentList(Long commentId);
    Boolean deleteCommentById(Long commentId);
}

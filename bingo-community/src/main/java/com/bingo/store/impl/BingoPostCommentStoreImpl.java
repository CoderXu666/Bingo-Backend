package com.bingo.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoPostCommentMapper;
import com.bingo.pojo.po.BingoPostComment;
import com.bingo.store.BingoPostCommentStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-13
 */
@Service
public class BingoPostCommentStoreImpl extends ServiceImpl<BingoPostCommentMapper, BingoPostComment> implements BingoPostCommentStore {

}

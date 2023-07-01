package com.bingo.store.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoTopicCommentMapper;
import com.bingo.pojo.po.BingoTopicComment;
import com.bingo.store.BingoTopicCommentStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-01
 */
@Service
public class BingoTopicCommentStoreImpl extends ServiceImpl<BingoTopicCommentMapper, BingoTopicComment> implements BingoTopicCommentStore {

}

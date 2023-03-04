package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoPostMapper;
import com.bingo.pojo.po.BingoPost;
import com.bingo.service.BingoPostService;
import com.bingo.store.BingoPostStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-04
 */
@Service
public class BingoPostServiceImpl extends ServiceImpl<BingoPostMapper, BingoPost> implements BingoPostService {
    @Autowired
    private BingoPostStore postStore;

    @Override
    public boolean saveInfo(BingoPost bingoPost) {
        return postStore.savePost(bingoPost);
    }
}

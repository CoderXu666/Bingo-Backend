package com.bingo.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoPostMapper;
import com.bingo.pojo.po.BingoPost;
import com.bingo.store.BingoPostStore;
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
public class BingoPostStoreImpl extends ServiceImpl<BingoPostMapper, BingoPost> implements BingoPostStore {

    @Override
    public boolean savePost(BingoPost bingoPost) {
        int insert = baseMapper.insert(bingoPost);
        return true;
    }
}

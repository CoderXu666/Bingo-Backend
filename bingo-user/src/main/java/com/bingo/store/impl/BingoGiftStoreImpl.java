package com.bingo.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoGiftMapper;
import com.bingo.pojo.po.user.BingoGift;
import com.bingo.store.BingoGiftStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 礼物权益表 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-08
 */
@Service
public class BingoGiftStoreImpl extends ServiceImpl<BingoGiftMapper, BingoGift> implements BingoGiftStore {
    /**
     * 礼品权益概览
     */
    @Override
    public List<BingoGift> getList() {
        return this.list();
    }
}

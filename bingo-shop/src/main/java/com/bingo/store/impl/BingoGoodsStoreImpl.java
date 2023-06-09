package com.bingo.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoGoodsMapper;
import com.bingo.pojo.po.BingoGoods;
import com.bingo.store.BingoGoodsStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-09
 */
@Service
public class BingoGoodsStoreImpl extends ServiceImpl<BingoGoodsMapper, BingoGoods> implements BingoGoodsStore {
    /**
     * 查询商品列表
     */
    @Override
    public List<BingoGoods> queryList() {
        return this.list();
    }
}

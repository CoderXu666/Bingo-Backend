package com.bingo.store.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.anno.DoubleCache;
import com.bingo.constant.CachePartition;
import com.bingo.enums.CacheType;
import com.bingo.mapper.BingoUserMapper;
import com.bingo.pojo.po.BingoUser;
import com.bingo.store.BingoUserStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
@Service
public class BingoUserStoreImpl extends ServiceImpl<BingoUserMapper, BingoUser> implements BingoUserStore {

    /**
     * 根据userName查询用户信息
     */
    @Override
    @DoubleCache(cacheName = CachePartition.BINGO_USER, key = "#userName", type = CacheType.FULL)
    public BingoUser findUserInfo(String userName) {
        QueryWrapper<BingoUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        BingoUser bingoUser = this.getOne(queryWrapper);
        return bingoUser;
    }
}

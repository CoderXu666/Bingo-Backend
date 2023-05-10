package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.anno.DoubleCache;
import com.bingo.constant.CacheConstant;
import com.bingo.enums.CacheType;
import com.bingo.mapper.BingoUserStatisticsMapper;
import com.bingo.pojo.po.BingoUserStatistics;
import com.bingo.store.BingoUserStatisticsStore;
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
public class BingoUserStatisticsStoreImpl extends ServiceImpl<BingoUserStatisticsMapper, BingoUserStatistics> implements BingoUserStatisticsStore {

    /**
     * 根据userName查询用户信息
     */
    @Override
    @DoubleCache(cacheName = CacheConstant.BINGO_USER_STATISTICS, key = "#p0", type = CacheType.FULL)
    public BingoUserStatistics findUserSta(String userName) {
        QueryWrapper<BingoUserStatistics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        BingoUserStatistics bingoUserStatistics = this.getOne(queryWrapper);
        return bingoUserStatistics;
    }
}

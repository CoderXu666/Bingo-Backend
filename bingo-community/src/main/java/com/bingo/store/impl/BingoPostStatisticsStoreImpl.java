package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoPostStatisticsMapper;
import com.bingo.pojo.po.BingoPostStatistics;
import com.bingo.store.BingoPostStatisticsStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-18
 */
@Service
public class BingoPostStatisticsStoreImpl extends ServiceImpl<BingoPostStatisticsMapper, BingoPostStatistics> implements BingoPostStatisticsStore {

    /**
     * 展示用户最新的帖子（分页10条）
     */
    @Override
    public List<BingoPostStatistics> findPost(List<Long> ids) {
        QueryWrapper<BingoPostStatistics> queryWrapper = new QueryWrapper();
        queryWrapper.in("id",ids);
        List<BingoPostStatistics> list = this.list(queryWrapper);
        return list;
    }
}

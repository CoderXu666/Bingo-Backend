package com.bingo.store.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.pojo.po.BingoFollowRecord;
import com.bingo.store.BingoFollowRecordStore;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-09
 */
@Service
public class BingoFollowRecordStoreImpl extends ServiceImpl<BingoFollowRecordMapper, BingoFollowRecord> implements BingoFollowRecordStore {

    /**
     * 关注用户
     */
    @Override
    public Boolean saveFollow(BingoFollowRecord BingoFollowRecord) {
        return this.save(BingoFollowRecord);
    }

    /**
     * 查询用户的关注列表
     */
    @Override
    public List<BingoFollowRecord> findFollowList(Long userId) {
        QueryWrapper<BingoFollowRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id1", userId);
        queryWrapper.orderByDesc("create_time");
        return this.list(queryWrapper);
    }

    /**
     * 查询是否关注该用户
     */
    @Override
    public BingoFollowRecord getOneFollow(Long userId1, Long userId2) {
        QueryWrapper<BingoFollowRecord> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id1", userId1);
        queryWrapper.eq("user_id2", userId2);
        return this.getOne(queryWrapper);
    }

    /**
     * 关注、取消关注
     */
    @Override
    public Boolean updateFollow(BingoFollowRecord BingoFollowRecord) {
        return this.updateById(BingoFollowRecord);

    }
}

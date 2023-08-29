package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFollowRecordMapper;
import com.bingo.pojo.po.community.BingoFollowRecord;
import com.bingo.store.BingoFollowRecordStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户关注表 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
@Service
public class BingoFollowRecordStoreImpl extends ServiceImpl<BingoFollowRecordMapper, BingoFollowRecord> implements BingoFollowRecordStore {
    /**
     * 关注用户
     */
    @Override
    public Boolean saveFollowRecord(BingoFollowRecord followRecord) {
        return this.save(followRecord);
    }

    /**
     * 根据uid和goal_id查询关注记录
     */
    @Override
    public BingoFollowRecord findRecordByUserIdAndGoalId(Long uid, Long goalId) {
        QueryWrapper<BingoFollowRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        wrapper.eq("goal_id", goalId);
        return this.getOne(wrapper);
    }

    /**
     * 取消关注（根据id）
     */
    @Override
    public Boolean removeFollowRecord(Long recordId) {
        return this.removeById(recordId);
    }

    /**
     * 关注列表
     */
    @Override
    public Page<BingoFollowRecord> followList(Long uid, Integer current, Integer limit) {
        Page<BingoFollowRecord> page = new Page(current, limit);
        QueryWrapper<BingoFollowRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        return this.page(page, wrapper);
    }

    /**
     * 粉丝列表
     */
    @Override
    public Page<BingoFollowRecord> fanList(Long goalId, Integer current, Integer limit) {
        Page<BingoFollowRecord> page = new Page(current, limit);
        QueryWrapper<BingoFollowRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("goal_id", goalId);
        return this.page(page, wrapper);
    }


    /**
     * 互关列表（好友）
     */
    @Override
    public Page<BingoFollowRecord> friendList(Long uid, Long goalId, Integer current, Integer limit) {
        QueryWrapper<BingoFollowRecord> wrapper = new QueryWrapper<>();
        return null;
    }
}

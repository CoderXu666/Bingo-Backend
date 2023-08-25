package com.bingo.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFollowRecordMapper;
import com.bingo.pojo.po.community.BingoFollowRecord;
import com.bingo.store.BingoFollowRecordStore;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Boolean followUser(BingoFollowRecord followRecord) {
        return this.save(followRecord);
    }

    /**
     * 查询互关好友id
     */
    @Override
    public List<BingoFollowRecord> getFriendList(Long userId) {
        return null;
    }

    /**
     * 查询关注用户id
     */
    @Override
    public List<BingoFollowRecord> getFollowList(Long userId) {
        return null;
    }

    /**
     * 查询粉丝id
     */
    @Override
    public List<BingoFollowRecord> getFanList(Long userId) {
        return null;
    }
}

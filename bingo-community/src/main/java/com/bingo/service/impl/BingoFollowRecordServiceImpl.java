package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFollowRecordMapper;
import com.bingo.pojo.po.community.BingoFollowRecord;
import com.bingo.service.BingoFollowRecordService;
import com.bingo.store.BingoFollowRecordStore;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BingoFollowRecordServiceImpl extends ServiceImpl<BingoFollowRecordMapper, BingoFollowRecord> implements BingoFollowRecordService {
    @Autowired
    private BingoFollowRecordStore followRecordStore;

    /**
     * 关注用户
     */
    @Override
    public Boolean followUser(Long userId, Long goalId) {
        BingoFollowRecord followRecord = new BingoFollowRecord();
        followRecord.setUserId(userId);
        followRecord.setGoalId(goalId);
        return followRecordStore.followUser(followRecord);
    }
}

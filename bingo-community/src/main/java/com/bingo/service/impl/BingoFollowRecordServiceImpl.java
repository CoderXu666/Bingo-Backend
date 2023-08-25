package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFollowRecordMapper;
import com.bingo.pojo.po.community.BingoFollowLog;
import com.bingo.pojo.po.community.BingoFollowRecord;
import com.bingo.service.BingoFollowRecordService;
import com.bingo.store.BingoFollowLogStore;
import com.bingo.store.BingoFollowRecordStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;

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
    @Autowired
    private BingoFollowLogStore followLogStore;

    /**
     * 关注用户
     */
    @Override
    public Boolean followUser(Long userId, Long goalId) {
        // 查询是否关注过
        BingoFollowRecord record = followRecordStore.findRecordByUserIdAndGoalId(userId, goalId);

        // 不存在：没关注过
        if (ObjectUtils.isEmpty(record)) {
            BingoFollowRecord followRecord = new BingoFollowRecord();
            followRecord.setUserId(userId);
            followRecord.setGoalId(goalId);
            followRecordStore.saveFollowRecord(followRecord);
            return followLogStore.save(new BingoFollowLog(null, userId, goalId, "ADD", new Date()));
        }

        // 存在：关注过
        followRecordStore.removeFollowRecord(record.getId());
        return followLogStore.save(new BingoFollowLog(null, userId, goalId, "DELETE", new Date()));
    }
}

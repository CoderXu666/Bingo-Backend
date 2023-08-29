package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoFollowRecordMapper;
import com.bingo.pojo.po.community.BingoFollowLog;
import com.bingo.pojo.po.community.BingoFollowRecord;
import com.bingo.pojo.vo.user.UserVO;
import com.bingo.service.BingoFollowRecordService;
import com.bingo.store.BingoFollowLogStore;
import com.bingo.store.BingoFollowRecordStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private UserFeign userFeign;

    /**
     * 关注用户
     */
    @Override
    public Boolean followUser(Long uid, Long goalId) {
        // 查询是否关注过
        BingoFollowRecord record = followRecordStore.findRecordByUserIdAndGoalId(uid, goalId);

        // 不存在：没关注过
        if (ObjectUtils.isEmpty(record)) {
            BingoFollowRecord followRecord = new BingoFollowRecord();
            followRecord.setUid(uid);
            followRecord.setGoalId(goalId);
            followRecordStore.saveFollowRecord(followRecord);
            return followLogStore.save(new BingoFollowLog(null, uid, goalId, "ADD", new Date()));
        }

        // 存在：关注过
        followRecordStore.removeFollowRecord(record.getId());
        return followLogStore.save(new BingoFollowLog(null, uid, goalId, "DELETE", new Date()));
    }

    /**
     * 关注列表
     */
    @Override
    public Map<String, Object> followList(Long uid, Integer current, Integer limit) {
        Page<BingoFollowRecord> recordPage = followRecordStore.followList(uid, current, limit);
        List<Long> followIds = recordPage.getRecords().stream().map(item -> item.getUid()).collect(Collectors.toList());
        List<UserVO> userInfoList = userFeign.getUserByIds(followIds).getData();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", userInfoList);
        resultMap.put("count", recordPage.getTotal());
        return resultMap;
    }

    /**
     * 粉丝列表
     */
    @Override
    public Map<String, Object> fanList(Long goalId, Integer current, Integer limit) {
        Page<BingoFollowRecord> recordPage = followRecordStore.fanList(goalId, current, limit);
        List<Long> fanIds = recordPage.getRecords().stream().map(item -> item.getUid()).collect(Collectors.toList());
        List<UserVO> userInfoList = userFeign.getUserByIds(fanIds).getData();
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("list", userInfoList);
        resultMap.put("count", recordPage.getTotal());
        return resultMap;
    }

    /**
     * 互关列表（好友）
     */
    @Override
    public Map<String, Object> friendList(Long uid, Long goalId, Integer current, Integer limit) {
        Page<BingoFollowRecord> recordPage = followRecordStore.friendList(uid, goalId, current, limit);
        return null;
    }
}

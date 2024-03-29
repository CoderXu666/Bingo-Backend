package com.bingo.store;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.community.BingoFollowRecord;

import java.util.List;

/**
 * <p>
 * 用户关注表 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
public interface BingoFollowRecordStore extends IService<BingoFollowRecord> {
    Boolean saveFollowRecord(BingoFollowRecord followRecord);

    BingoFollowRecord findRecordByUserIdAndGoalId(Long uid, Long goalId);

    Boolean removeFollowRecord(Long recordId);

    Page<BingoFollowRecord> followList(Long uid, Integer current, Integer limit);

    Page<BingoFollowRecord> fanList(Long goalId, Integer current, Integer limit);

    Page<BingoFollowRecord> friendList(Long uid, Long goalId, Integer current, Integer limit);
}

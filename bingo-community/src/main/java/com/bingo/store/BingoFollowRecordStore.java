package com.bingo.store;

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

    BingoFollowRecord findRecordByUserIdAndGoalId(Long userId, Long goalId);

    Boolean removeFollowRecord(Long recordId);
}

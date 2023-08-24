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
    List<BingoFollowRecord> getFriendList(Long userId);

    List<BingoFollowRecord> getFollowList(Long userId);

    List<BingoFollowRecord> getFanList(Long userId);
}

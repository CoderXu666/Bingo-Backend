package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoFollowRecord;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-09
 */
public interface BingoFollowRecordStore extends IService<BingoFollowRecord> {

    Boolean saveFollow(BingoFollowRecord BingoFollowRecord);

    List<BingoFollowRecord> findFollowList(Long userId);

    BingoFollowRecord getOneFollow(Long userId1, Long userId2);

    Boolean updateFollow(BingoFollowRecord BingoFollowRecord);
}

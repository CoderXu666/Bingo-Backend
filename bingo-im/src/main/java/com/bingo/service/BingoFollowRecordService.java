package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoFollowRecord;

import java.util.Map;

/**
 * <p>
 * 用户关注表 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
public interface BingoFollowRecordService extends IService<BingoFollowRecord> {
    Map<String, Object> getChatList(Long userId);
}

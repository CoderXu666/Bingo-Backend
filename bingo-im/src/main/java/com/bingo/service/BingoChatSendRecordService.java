package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.BingoChatSendRecord;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 好友聊天记录表 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
public interface BingoChatSendRecordService extends IService<BingoChatSendRecord> {

    Map<Long, List<BingoChatSendRecord>> getChatRecordByUserId(Long userId, List<Long> ids);
}

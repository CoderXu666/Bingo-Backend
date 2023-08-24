package com.bingo.store;

import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 好友聊天记录表 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
public interface BingoChatSendRecordStore extends IService<BingoChatSendRecord> {

    List<BingoChatSendRecord> getSendRecordList(Long userId);
}

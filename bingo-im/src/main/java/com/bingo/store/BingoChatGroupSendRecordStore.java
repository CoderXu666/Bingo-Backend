package com.bingo.store;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.im.BingoChatGroupSendRecord;

import java.util.List;

/**
 * <p>
 * 聊天记录表（群聊） 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-26
 */
public interface BingoChatGroupSendRecordStore extends IService<BingoChatGroupSendRecord> {

    List<BingoChatGroupSendRecord> getSendRecordList(Long id);
}

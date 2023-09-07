package com.bingo.adapter;

import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-07  14:31
 * @Description: 聊天消息转换（适配器模式）
 * @Version: 1.0
 */
public class ChatRecordAdapter {
    /**
     * 聊天消息转换（单聊）
     */
    public static BingoChatSendRecord buildChatRecordPO(ChatRecordDTO chatMsgDTO) {
        BingoChatSendRecord sendRecord = new BingoChatSendRecord();
        BeanUtils.copyProperties(chatMsgDTO, sendRecord);
        sendRecord.setCreateTime(new Date());
        return sendRecord;
    }
}

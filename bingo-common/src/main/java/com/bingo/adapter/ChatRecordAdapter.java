package com.bingo.adapter;

import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.pojo.po.im.BingoChatSendRecord;

import java.util.Date;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-07  14:31
 * @Description: 聊天消息转换
 * @Version: 1.0
 */
public class ChatRecordAdapter {
    public static BingoChatSendRecord buildChatRecordPO(ChatRecordDTO chatMsgDTO) {
        return BingoChatSendRecord.builder()
                .uid(chatMsgDTO.getUid())
                .goalId(chatMsgDTO.getGoalId())
                .chatContent(chatMsgDTO.getChatContent())
                .type(chatMsgDTO.getType())
                .createTime(new Date())
                .build();
    }
}

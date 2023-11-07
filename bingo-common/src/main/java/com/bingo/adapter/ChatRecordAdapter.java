package com.bingo.adapter;

import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.pojo.po.im.BingoChatSendRecord;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-07  14:31
 * @Description: 适配器模式：聊天消息相关
 * @Version: 1.0
 */
public class ChatRecordAdapter {
    public static BingoChatSendRecord buildChatRecordPO(ChatRecordDTO chatMsgDTO) {
        return BingoChatSendRecord.builder()
                .uid(chatMsgDTO.getUid())
                .goalId(chatMsgDTO.getGoalId())
                .chatContent(chatMsgDTO.getChatContent())
                .type(chatMsgDTO.getType())
                .build();
    }
}

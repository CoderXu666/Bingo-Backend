package com.bingo.strategy.chat;

import com.bingo.enums.ChatRecordEnum;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:38
 * @Description: 礼物策略Handler
 * @Version: 1.0
 */
@Slf4j
@Component
public class ChatGiftHandler extends AbstractChatStrategy {
    /**
     * 当前策略枚举
     */
    @Override
    ChatRecordEnum getEnum() {
        return ChatRecordEnum.GIFT;
    }

    @Override
    public Boolean saveChatRecord(ChatRecordDTO recordDTO) {
        return null;
    }
}

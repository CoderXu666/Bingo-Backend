package com.bingo.strategy.chat;

import com.bingo.enums.ChatRecordEnum;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:38
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Component
public class ChatVideoHandler extends AbstractChatStrategy {
    /**
     * 当前策略枚举
     */
    @Override
    public ChatRecordEnum getEnum() {
        return ChatRecordEnum.VIDEO;
    }

    @Override
    public Boolean saveChatRecord(ChatRecordDTO recordDTO) {
        return null;
    }
}

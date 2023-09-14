package com.bingo.strategy.chat;

import com.bingo.enums.ChatRecordEnum;
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
public class ChatFileHandler extends AbstractChatStrategy {
    /**
     * 当前策略类型枚举
     */
    @Override
    ChatRecordEnum getEnum() {
        return ChatRecordEnum.FILE;
    }

    @Override
    Boolean saveChatRecord() {
        return null;
    }
}

package com.bingo.strategy.chat;

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

    @Override
    Boolean saveChatRecord() {
        return null;
    }
}

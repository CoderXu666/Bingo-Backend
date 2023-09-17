package com.bingo.strategy.chat;

import com.bingo.enums.ChatRecordEnum;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:38
 * @Description: 文字策略Handler
 * @Version: 1.0
 */
@Slf4j
@Component
public class ChatFontHandler extends AbstractChatStrategy {
    /**
     * 当前策略枚举
     */
    @Override
    ChatRecordEnum getEnum() {
        return ChatRecordEnum.FONT;
    }

    /**
     * 处理会话窗口，更新未读数量
     */
    @Override
    public Boolean handleChatRecord(BingoChatSendRecord sendRecord, MultipartFile file) {
        return null;
    }
}

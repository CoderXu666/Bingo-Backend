package com.bingo.strategy.chat;

import com.bingo.enums.ChatRecordEnum;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:38
 * @Description: 视频通话策略Handler
 * @Version: 1.0
 */
@Slf4j
@Component
public class ChatLiveHandler extends AbstractChatStrategy {
    /**
     * 当前策略枚举
     */
    @Override
    ChatRecordEnum getEnum() {
        return ChatRecordEnum.GIFT;
    }

    /**
     * 处理直播逻辑
     */
    @Override
    public Boolean handleChatRecord(BingoChatSendRecord sendRecord) {
        return null;
    }
}

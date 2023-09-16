package com.bingo.strategy.chat;

import com.bingo.enums.ChatRecordEnum;
import com.bingo.pojo.dto.im.ChatRecordDTO;

import javax.annotation.PostConstruct;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:18
 * @Description: 策略模式（保存聊天功能）：文字、音频、图片、视频、文件、视频通话等
 * @Version: 1.0
 * -------------------------------------------------------------------
 * 1个枚举对应1个策略Handler
 */
public abstract class AbstractChatStrategy {
    /**
     * 注册目标策略Handler
     */
    @PostConstruct
    private void initStrategyHandler() {
        StrategyChatFactory.register(getEnum().getCode(), this);
    }

    /**
     * 获取当前策略类型枚举
     */
    abstract ChatRecordEnum getEnum();

    /**
     * 保存聊天记录
     */
    public abstract Boolean saveChatRecord(ChatRecordDTO recordDTO);
}

package com.bingo.strategy.chat;

import com.bingo.enums.ChatRecordEnum;

import javax.annotation.PostConstruct;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:18
 * @Description: 策略模式（保存聊天功能）：文字、音频、文件（图片、视频）、视频通话等
 * @Version: 1.0
 */
public abstract class AbstractChatStrategy {
    /**
     * 初始化策略类 Map
     */
    @PostConstruct
    private void initStrategyHandler() {
        StrategyChatFactory.register(getEnum().getCode(), this);
    }

    /**
     * 获取策略类型枚举
     */
    abstract ChatRecordEnum getEnum();

    /**
     * 保存聊天记录
     */
    abstract Boolean saveChatRecord();
}

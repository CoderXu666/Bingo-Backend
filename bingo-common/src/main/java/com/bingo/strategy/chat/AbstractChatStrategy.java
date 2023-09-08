package com.bingo.strategy.chat;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:18
 * @Description: 策略模式（保存聊天功能）：文字、音频、文件（图片、视频）、视频通话等
 * @Version: 1.0
 */
public abstract class AbstractChatStrategy {
    /**
     * 保存聊天记录
     */
    abstract Boolean saveChatRecord();
}

package com.bingo.adapter;

import com.bingo.pojo.po.im.BingoChatShow;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  16:08
 * @Description: 适配器模式：聊天会话相关
 * @Version: 1.0
 */
public class ChatShowAdapter {
    public static BingoChatShow buildChatShowPO(Long uid, Long goalId) {
        return BingoChatShow.builder()
                .uid(uid)
                .goalId(goalId)
                .chatType(0)
                .unreadCount(0)
                .build();
    }
}

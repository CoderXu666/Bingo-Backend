package com.bingo.pojo.dto;

import lombok.Data;

@Data
public class FriendChatDTO {
    /**
     * 发送者id
     */
    private Long userId1;

    /**
     * 接收者id
     */
    private Long userId2;

    /**
     * 聊天类型（0：文字/图片、1：表情包、2：语音/视频）
     */
    private Integer chatType;

    /**
     * 聊天内容
     */
    private String chatContent;
}

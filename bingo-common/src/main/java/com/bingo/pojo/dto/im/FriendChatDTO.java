package com.bingo.pojo.dto.im;

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
     * 消息类型
     */
    private Integer chatType;

    /**
     * 消息内容
     */
    private String chatContent;
}

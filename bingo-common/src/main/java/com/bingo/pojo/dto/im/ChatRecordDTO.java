package com.bingo.pojo.dto.im;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/5/29 21:03
 * @Version 1.0
 * @Description: 聊天消息入参
 */
@Data
public class ChatRecordDTO {
    /**
     * 发送方id
     */
    private Long uid;

    /**
     * 接收方id
     */
    private Long goalId;

    /**
     * 消息类型
     */
    private Integer type;

    /**
     * 消息内容
     */
    private String chatContent;
}

package com.bingo.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class FriendChatDTO {
    /**
     * 用户ID（关联用户ID: 发送者）
     */
    private Long userId;

    /**
     * 聊天类型（0：文字/图片、1：表情包、2：语音/视频）
     */
    private Integer chatType;

    /**
     * 聊天内容
     */
    private String chatContent;
}

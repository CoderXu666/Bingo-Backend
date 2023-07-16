package com.bingo.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class FriendChatDTO {
    /**
     * 用户ID（关联用户ID: 发送者）
     */
    @TableField("user_id1")
    private Long userId1;

    /**
     * 用户ID（关联用户ID: 接受者）
     */
    @TableField("user_id2")
    private Long userId2;

    /**
     * 聊天类型（0：文字/图片、1：表情包、2：语音/视频）
     */
    @TableField("chat_type")
    private Integer chatType;

    /**
     * 聊天内容
     */
    @TableField("chat_content")
    private String chatContent;
}

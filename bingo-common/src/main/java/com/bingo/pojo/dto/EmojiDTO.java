package com.bingo.pojo.dto;

import lombok.Data;

@Data
public class EmojiDTO {

    /**
     * 用户ID（关联用户ID）
     */
    private Long userId;

    /**
     * 表情包
     */
    private String emojiUrl;
}

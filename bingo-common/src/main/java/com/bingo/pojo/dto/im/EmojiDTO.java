package com.bingo.pojo.dto.im;

import lombok.Data;

@Data
public class EmojiDTO {

    /**
     * 用户ID（关联用户ID）
     */
    private Long uid;

    /**
     * 表情包
     */
    private String emojiUrl;
}

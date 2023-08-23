package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_chat_emoji")
public class BingoChatEmoji implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户ID（关联用户ID）
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 表情包
     */
    @TableField("emoji_url")
    private String emojiUrl;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;
}

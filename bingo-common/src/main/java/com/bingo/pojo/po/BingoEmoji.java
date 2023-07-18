package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@TableName("bingo_emoji")
public class BingoEmoji implements Serializable {

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
    private LocalDateTime createTime;


}

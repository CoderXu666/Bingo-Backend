package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_post_statistics")
public class BingoPostStatistics implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id（与用帖子id一致）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 帖子点赞数
     */
    @TableField("like_count")
    private Integer likeCount;

    /**
     * 帖子评论数
     */
    @TableField("comment_count")
    private Integer commentCount;

    /**
     * 帖子转发数
     */
    @TableField("forward_count")
    private Integer forwardCount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除标识(未删除:0 已删除:1)
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}

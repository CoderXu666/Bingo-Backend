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
 * @since 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_topic")
public class BingoTopic implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 关联用户ID
     */
    @TableField("user_id")
    private String userId;

    /**
     * 关联话题评论ID
     */
    @TableField("topic_comment_id")
    private String topicCommentId;

    /**
     * 话题内容
     */
    @TableField("topic_content")
    private String topicContent;

    /**
     * 话题图片
     */
    @TableField("topic_picture")
    private String topicPicture;

    /**
     * 话题点赞数
     */
    @TableField("like_count")
    private Long likeCount;

    /**
     * 话题转发数
     */
    @TableField("forward_count")
    private Long forwardCount;

    /**
     * 话题评论数
     */
    @TableField("comment_count")
    private Long commentCount;

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
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}

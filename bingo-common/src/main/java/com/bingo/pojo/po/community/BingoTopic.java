package com.bingo.pojo.po.community;

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
    private Long userId;

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
     * 话题标签
     */
    @TableField("topic_tag")
    private String topicTag;

    /**
     * 话题点赞数
     */
    @TableField("like_count")
    private Integer likeCount;

    /**
     * 话题转发数
     */
    @TableField("forward_count")
    private Integer forwardCount;

    /**
     * 话题评论数
     */
    @TableField("comment_count")
    private Integer commentCount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}

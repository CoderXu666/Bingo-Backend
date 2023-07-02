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
 * @author 周英俊
 * @since 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_topic_comment")
public class BingoTopicComment implements Serializable {

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
     * 关联话题ID
     */
    @TableField("topic_id")
    private String topicId;

    /**
     * 父ID(确定当前评论位置)
     */
    @TableField("comment_parent_id")
    private String commentParentId;

    /**
     * 话题评论内容
     */
    @TableField("comment_content")
    private String commentContent;

    /**
     * 话题评论图片
     */
    @TableField("comment_picture")
    private String commentPicture;

    /**
     * 话题评论点赞数
     */
    @TableField("comment_like_count")
    private Integer commentLikeCount;

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

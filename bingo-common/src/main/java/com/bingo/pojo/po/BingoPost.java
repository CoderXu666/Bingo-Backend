package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
 * @since 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_post")
public class BingoPost implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 账号
     */
    @TableField("user_id")
    private String userId;

    /**
     * 帖子文案
     */
    @TableField("post_font")
    private String postFont;

    /**
     * 帖子图片/视频链接
     */
    @TableField("post_url")
    private String postUrl;

    /**
     * 关联话题（#坤坤）
     */
    @TableField("post_topic")
    private String postTopic;

    /**
     * 点赞数
     */
    @TableField("like_count")
    private Long likeCount;

    /**
     * 评论数
     */
    @TableField("comment_count")
    private Long commentCount;

    /**
     * 转发数
     */
    @TableField("forward_count")
    private Long forwardCount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除标识（0未删除、1已删除）
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}

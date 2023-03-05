package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-04
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
    private String id;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 帖子文字
     */
    @TableField("post_font")
    private String postFont;

    /**
     * 帖子图片/视频链接
     */
    @TableField("post_url")
    private String postUrl;

    /**
     * 帖子评论id（关联评论表）
     */
    @TableField("comment_id")
    private String commentId;

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
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 删除标识（0未删除、1已删除）
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}

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
    @TableField("uid")
    private Long uid;

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

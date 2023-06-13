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
 * @since 2023-06-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_post_comment")
public class BingoPostComment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id（与帖子表主键id对应）
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 账号id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 评论文字内容
     */
    @TableField("comment_font")
    private String commentFont;

    /**
     * 评论视频、图片
     */
    @TableField("comment_url")
    private String commentUrl;

    /**
     * 评论时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 修改时间
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

package com.bingo.pojo.po.community;

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
 * @since 2023-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_dynamic")
public class BingoDynamic implements Serializable {

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
    @TableField("dynamic_font")
    private String dynamicFont;

    /**
     * 帖子图片/视频链接
     */
    @TableField("dynamic_url")
    private String dynamicUrl;

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

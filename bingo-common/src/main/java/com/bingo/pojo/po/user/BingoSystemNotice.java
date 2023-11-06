package com.bingo.pojo.po.user;

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
 * @since 2023-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_system_notice")
public class BingoSystemNotice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 关联用户表主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 通知类型（1:系统通知，2:点赞通知，3:关注通知）
     */
    @TableField("notice_type")
    private Boolean noticeType;

    /**
     * 通知内容
     */
    @TableField("notice_content")
    private String noticeContent;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 删除标识（1：未删除，2：已删除）
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;
}

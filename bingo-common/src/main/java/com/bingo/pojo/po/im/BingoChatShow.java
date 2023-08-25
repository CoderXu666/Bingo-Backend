package com.bingo.pojo.po.im;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 聊天窗口列表（展示）
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_chat_show")
public class BingoChatShow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 目标id（用户/群组）
     */
    @TableField("goal_id")
    private Long goalId;

    /**
     * 未读消息数量
     */
    @TableField("unread_count")
    private Long unreadCount;

    /**
     * 最新接收时间（显示排序）
     */
    @TableField("receive_time")
    private Date receiveTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}

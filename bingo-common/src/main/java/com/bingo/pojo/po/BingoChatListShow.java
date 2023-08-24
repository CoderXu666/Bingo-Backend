package com.bingo.pojo.po;

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
@TableName("bingo_chat_list_show")
public class BingoChatListShow implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 发送方id
     */
    @TableField("user_id1")
    private Long userId1;

    /**
     * 接收方id
     */
    @TableField("user_id2")
    private Long userId2;

    /**
     * 最新接收消息时间（显示排序）
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

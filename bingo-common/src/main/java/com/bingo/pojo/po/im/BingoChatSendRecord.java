package com.bingo.pojo.po.im;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 好友聊天记录表
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_chat_send_record")
public class BingoChatSendRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 发送者id
     */
    @TableField("uid")
    private Long userId;

    /**
     * 目标id
     */
    @TableField("goal_id")
    private Long goalId;

    /**
     * 聊天内容
     */
    @TableField("chat_content")
    private String chatContent;

    /**
     * 消息类型（0：文字、1：表情包、2：文件（图片等）4：语音/视频）、5：内容分享
     */
    @TableField("chat_content_type")
    private Integer chatContentType;

    /**
     * 发送状态（0：成功，1：失败）
     */
    @TableField("send_status")
    private Boolean sendStatus;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

}

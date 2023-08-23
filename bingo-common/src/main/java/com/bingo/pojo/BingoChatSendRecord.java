package com.bingo.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
    @TableField("user_id1")
    private Long userId1;

    /**
     * 接收者id/群聊id
     */
    @TableField("user_id2")
    private Long userId2;

    /**
     * 关联标识（user_id1:user_id2）
     */
    @TableField("relation")
    private String relation;

    /**
     * 聊天内容
     */
    @TableField("chat_content")
    private String chatContent;

    /**
     * 消息类型（0：文字、1：表情包、2：文件（图片等）4：语音/视频）、5：内容分享
     */
    @TableField("chat_type")
    private Boolean chatType;

    /**
     * 发送状态（0：成功，1：失败）
     */
    @TableField("status")
    private Boolean status;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 删除标识
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}

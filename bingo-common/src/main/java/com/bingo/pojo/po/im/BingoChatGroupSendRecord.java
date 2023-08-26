package com.bingo.pojo.po.im;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 聊天记录表（群聊）
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_chat_group_send_record")
public class BingoChatGroupSendRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 群组id
     */
    @TableField("group_id")
    private Long groupId;

    /**
     * 用户id
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 聊天内容
     */
    @TableField("chat_content")
    private String chatContent;

    /**
     * 文件链接
     */
    @TableField("chat_url")
    private String chatUrl;

    /**
     * 消息类型（0：文字、1：表情包、2：文件（图片等）4：语音/视频）、5：内容分享
     */
    @TableField("chat_content_type")
    private Integer chatContentType;

    /**
     * 发送时间
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

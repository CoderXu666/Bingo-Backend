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
 * @since 2023-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_friend_chat")
public class BingoFriendChat implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 好友标识（userId1:userId2）
     */
    @TableField("relation")
    private String relation;

    /**
     * 用户ID（关联用户ID: 发送者）
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 聊天类型（0：文字/图片、1：表情包、2：语音/视频）
     */
    @TableField("chat_type")
    private Integer chatType;

    /**
     * 聊天内容
     */
    @TableField("chat_content")
    private String chatContent;

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

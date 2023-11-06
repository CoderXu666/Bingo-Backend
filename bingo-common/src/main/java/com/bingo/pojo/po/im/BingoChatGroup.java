package com.bingo.pojo.po.im;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 群聊信息表
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_chat_group")
public class BingoChatGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 群主id
     */
    @TableField("uid")
    private Long uid;

    /**
     * 群名称
     */
    @TableField("group_name")
    private String groupName;

    /**
     * 群头像
     */
    @TableField("group_avatar")
    private String groupAvatar;

    /**
     * 群人数
     */
    @TableField("group_member_count")
    private Integer groupMemberCount;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    /**
     * 删除标识
     */
    @TableField("delete")
    private Boolean delete;


}

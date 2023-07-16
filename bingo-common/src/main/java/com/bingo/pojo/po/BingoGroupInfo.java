package com.bingo.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_group_chat")
public class BingoGroupInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 群主ID
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 群名
     */
    @TableField("group_name")
    private String groupName;

    /**
     * 群头像
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 群成员
     */
    @TableField("group_member")
    private String groupMember;

    /**
     * 群公告
     */
    @TableField("group_notice")
    private String groupNotice;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;

    /**
     * 删除标识
     */
    @TableField("delete")
    private Boolean delete;


}

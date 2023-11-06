package com.bingo.pojo.po.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Builder;
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
 * @since 2023-03-01
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_user")
public class BingoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "uid", type = IdType.ASSIGN_ID)
    private Long uid;

    /**
     * 账号id
     */
    @TableField("account_id")
    private String accountId;

    /**
     * 密码
     */
    @TableField("pass_word")
    private String passWord;

    /**
     * 用户名
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 头像
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

    /**
     * 在线状态（0：未在线，1：在线）
     */
    @TableField("online_status")
    private Integer onlineStatus;

    /**
     * 地区
     */
    @TableField("location")
    private String location;

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
     * 删除标识(未删除:0 已删除:1)
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

}

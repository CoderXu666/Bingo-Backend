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
 * @since 2023-03-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("bingo_user")
public class BingoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键Id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;

    /**
     * 账号
     */
    @TableField("user_id")
    private String userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    @TableField("pass_word")
    private String passWord;

    /**
     * 头像
     */
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 个性签名
     */
    @TableField("signature")
    private String signature;

    /**
     * 生日
     */
    @TableField("birth_day")
    private String birthDay;

    /**
     * 电话
     */
    @TableField("phone")
    private String phone;

    /**
     * 性别(男:1 女:0)
     */
    @TableField("gender")
    private Integer gender;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

    /**
     * 邮箱
     */
    @TableField("email")
    private String email;

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
     * 删除标识(未删除:0 已删除:1)
     */
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;

}

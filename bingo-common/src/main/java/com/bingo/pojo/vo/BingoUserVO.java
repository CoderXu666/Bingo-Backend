package com.bingo.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class BingoUserVO {
    /**
     * 主键Id
     */
    private Long id;

    /**
     * 账号
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 生日
     */
    private Date birthDay;

    /**
     * 电话
     */
    private String phone;

    /**
     * 性别(男:1 女:0)
     */
    private Integer gender;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private Date createTime;
}

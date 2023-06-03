package com.bingo.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author 徐志斌
 * @since 2023-03-01
 */
@Data
public class LoginUserDTO {
    /**
     * 账号
     */
    private String userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

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
}

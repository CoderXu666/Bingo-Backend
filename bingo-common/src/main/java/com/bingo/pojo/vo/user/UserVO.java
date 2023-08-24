package com.bingo.pojo.vo.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserVO {
    /**
     * 账号
     */
    private String accountId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 头像
     */
    private String avatarUrl;

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

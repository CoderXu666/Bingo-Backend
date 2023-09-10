package com.bingo.pojo.resp.user;

import lombok.Data;

import java.util.Date;

@Data
public class UserResp {
    /**
     * 主键id
     */
    private Long uid;

    /**
     * 账号
     */
    private String accountId;

    /**
     * 用户名
     */
    private String nickName;

    /**
     * 性别(男:1 女:0)
     */
    private Integer gender;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 地址
     */
    private String location;

    /**
     * 注册时间
     */
    private Date createTime;

    /*-------------------------------用户相关统计数据------------------------------*/

    /**
     * 喜欢数量
     */
    private Integer likeCount;

    /**
     * 关注数量
     */
    private Integer followCount;

    /**
     * 粉丝数量
     */
    private Integer fanCount;

    /**
     * 人气值
     */
    private Integer popularityCount;

    /**
     * go币余额
     */
    private Integer money;
}

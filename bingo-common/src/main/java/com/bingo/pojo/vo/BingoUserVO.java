package com.bingo.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class BingoUserVO {
    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String headUrl;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地址
     */
    private String address;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 性别(男:1 女:0)
     */
    private Integer gender;

    /**
     * 粉丝人数
     */
    private Long fanCount;

    /**
     * 关注人数
     */
    private Long followCount;

    /**
     * 点赞人数
     */
    private Long likeCount;


}

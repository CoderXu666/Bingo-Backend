package com.bingo.pojo.vo;

import lombok.Data;

@Data
public class BingoFollowVO {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 用户名
     */
    private String userName;

}

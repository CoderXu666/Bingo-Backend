package com.bingo.pojo.vo.im;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/8/24 22:31
 * @Version 1.0
 * @Description: 聊天列表显示
 */
@Data
public class ChatShowResp {
    /**
     * 用户id
     */
    private Long uid;

    /**
     * 名称（用户，群聊）
     */
    private String itemName;

    /**
     * 头像URL
     */
    private String avatarUrl;

    /**
     * 目标类型（0：用户，1：群组）
     */
    private Integer type;

    /**
     * 地址
     */
    private String location;
}

package com.bingo.pojo.vo;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/6/4 19:30
 * @Version 1.0
 * @Description: PostVO
 */
@Data
public class PostVO {
    /**
     * 账号id
     */
    private String userId;
    private String avatarUrl;
    private Integer likeCount;
    private Integer commentCount;
    private String postFont;
    private String postUrl;
}

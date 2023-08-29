package com.bingo.pojo.vo.community;

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
     * id
     */
    private Long id;
    /**
     * 账号id
     */
    private String uid;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 头像url
     */
    private String avatarUrl;
    /**
     * 点赞数
     */
    private Integer likeCount;
    /**
     * 评论数
     */
    private Integer commentCount;
    /**
     * 帖子文字内容
     */
    private String postFont;
    /**
     * 帖子图片/视频内容
     */
    private String postUrl;
}

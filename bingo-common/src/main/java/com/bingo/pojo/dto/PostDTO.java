package com.bingo.pojo.dto;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-13
 */
@Data
public class PostDTO {
    /**
     * 账号
     */
    private String userId;

    /**
     * 帖子文案
     */
    private String postFont;

    /**
     * 帖子图片/视频链接
     */
    private String postUrl;

    /**
     * 关联话题（#坤坤）
     */
    private String postTopic;

    /**
     * 点赞数
     */
    private Long likeCount;

    /**
     * 评论数
     */
    private Long commentCount;

    /**
     * 转发数
     */
    private Long forwardCount;

}

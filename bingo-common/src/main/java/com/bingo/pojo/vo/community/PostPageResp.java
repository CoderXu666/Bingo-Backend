package com.bingo.pojo.vo.community;

import lombok.Data;

import java.util.Date;

@Data
public class PostPageResp {

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

    /**
     * 创建时间
     */
    private Date createTime;

}

package com.bingo.pojo.vo;

import lombok.Data;
import java.util.Date;

@Data
public class BingoTopicVO {
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
     * 话题内容
     */
    private String topicContent;

    /**
     * 话题图片
     */
    private String topicPicture;

    /**
     * 话题标签
     */
    private String topicTag;

    /**
     * 话题点赞数
     */
    private Integer likeCount;

    /**
     * 话题转发数
     */
    private Integer forwardCount;

    /**
     * 话题评论数
     */
    private Integer commentCount;

    /**
     * 创建时间
     */
    private Date createTime;

}

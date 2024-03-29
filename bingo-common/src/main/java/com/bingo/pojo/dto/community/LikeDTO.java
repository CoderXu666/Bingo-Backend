package com.bingo.pojo.dto.community;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/5/27 15:53
 * @Version 1.0
 * @Description: LikeDTO
 */
@Data
public class LikeDTO {
    /**
     * 被点赞帖子id
     */
    private Long postId;
    /**
     * 被点赞人
     */
    private String uid;
    /**
     * 发起点赞人
     */
    private String likeUid;
}

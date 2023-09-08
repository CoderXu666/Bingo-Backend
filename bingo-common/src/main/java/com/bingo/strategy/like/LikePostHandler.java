package com.bingo.strategy.like;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:24
 * @Description: 点赞帖子相关
 * @Version: 1.0
 */
@Slf4j
@Component
public class LikePostHandler extends AbstractLikeStrategy {
    /**
     * 点赞帖子
     */
    @Override
    Boolean like() {
        return null;
    }

    /**
     * 点赞帖子评论
     */
    @Override
    Boolean likeComment() {
        return null;
    }
}

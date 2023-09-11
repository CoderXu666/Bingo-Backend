package com.bingo.strategy.like;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:18
 * @Description: 策略模式（点赞功能）：帖子、话题、评论
 * @Version: 1.0
 */
public abstract class AbstractLikeStrategy {
    /**
     * 点赞用户、帖子、话题
     */
    abstract Boolean like();
}

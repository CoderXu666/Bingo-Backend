package com.bingo.strategy.action;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:18
 * @Description: 策略模式（点赞、打赏、评论）：用户、帖子、话题
 * @Version: 1.0
 */
public abstract class AbstractActionStrategy {
    /**
     * 点赞
     */
    abstract Boolean doLike();

    /**
     * 打赏
     */
    abstract Boolean sendGift();

    /**
     * 评论
     */
    abstract Boolean doComment();
}

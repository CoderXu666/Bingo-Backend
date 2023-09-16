package com.bingo.strategy.action;

import com.bingo.enums.TargetEnum;

import javax.annotation.PostConstruct;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:18
 * @Description: 策略模式（点赞、打赏、评论）：用户、帖子、话题
 * @Version: 1.0
 */
public abstract class AbstractTargetStrategy {
    /**
     * 初始化策略类 Map
     */
    @PostConstruct
    private void initStrategyHandler() {
        StrategyActionFactory.register(getEnum().getCode(), this);
    }

    /**
     * 获取策略类型枚举
     */
    abstract TargetEnum getEnum();

    /**
     * 点赞行为
     */
    abstract Boolean doLike();

    /**
     * 打赏行为
     */
    abstract Boolean sendGift();

    /**
     * 评论行为
     */
    abstract Boolean doComment();
}

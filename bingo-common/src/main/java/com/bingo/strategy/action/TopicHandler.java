package com.bingo.strategy.action;

import com.bingo.enums.ActionEnum;
import com.bingo.enums.TargetEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:26
 * @Description: 话题行为
 * @Version: 1.0
 */
@Slf4j
@Component
public class TopicHandler extends AbstractTargetStrategy {
    /**
     * 返回当前策略处理器枚举
     */
    @Override
    TargetEnum getEnum() {
        return TargetEnum.TOPIC;
    }

    /**
     * 点赞话题
     */
    @Override
    Boolean doLike() {
        return null;
    }

    /**
     * 打赏礼物
     */
    @Override
    Boolean sendGift() {
        return null;
    }

    /**
     * 评论话题
     */
    @Override
    Boolean doComment() {
        return null;
    }
}

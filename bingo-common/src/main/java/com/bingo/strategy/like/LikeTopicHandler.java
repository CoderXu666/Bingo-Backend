package com.bingo.strategy.like;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:26
 * @Description: 点赞话题
 * @Version: 1.0
 */
@Slf4j
@Component
public class LikeTopicHandler extends AbstractLikeStrategy {
    /**
     * 点赞话题
     */
    @Override
    Boolean like() {
        return null;
    }
}

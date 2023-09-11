package com.bingo.strategy.like;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:24
 * @Description: 点赞用户
 * @Version: 1.0
 */
@Slf4j
@Component
public class LikeUserHandler extends AbstractLikeStrategy {
    /**
     * 点赞用户
     */
    @Override
    Boolean like() {
        return null;
    }
}

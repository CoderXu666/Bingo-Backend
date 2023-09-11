package com.bingo.strategy.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:24
 * @Description: 点赞帖子
 * @Version: 1.0
 */
@Slf4j
@Component
public class PostHandler extends AbstractActionStrategy {

    @Override
    Boolean doLike() {
        return null;
    }

    @Override
    Boolean sendGift() {
        return null;
    }

    @Override
    Boolean doComment() {
        return null;
    }
}

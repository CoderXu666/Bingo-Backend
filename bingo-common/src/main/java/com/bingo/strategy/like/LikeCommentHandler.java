package com.bingo.strategy.like;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:24
 * @Description: 点赞评论（评论还分多种，这里还是有点冗余了）
 * @Version: 1.0
 */
@Slf4j
@Component
public class LikeCommentHandler extends AbstractLikeStrategy {
    /**
     * 点赞评论
     */
    @Override
    Boolean like() {
        return null;
    }
}

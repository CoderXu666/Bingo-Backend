package com.bingo.strategy.action;

import com.bingo.enums.ChatRecordEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:24
 * @Description: 用户行为
 * @Version: 1.0
 */
@Slf4j
@Component
public class UserHandler extends AbstractActionStrategy {
    /**
     * 返回当前策略处理器枚举
     */
    @Override
    ChatRecordEnum getEnum() {
        return null;
    }

    /**
     * 点赞/喜欢用户
     */
    @Override
    Boolean doLike() {
        return null;
    }

    /**
     * 发送礼物
     */
    @Override
    Boolean sendGift() {
        return null;
    }

    /**
     * 留言板评论
     */
    @Override
    Boolean doComment() {
        return null;
    }
}

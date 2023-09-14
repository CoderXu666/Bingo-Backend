package com.bingo.enums;

import lombok.AllArgsConstructor;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-14  09:05
 * @Description: 行为枚举（点赞、评论、转发、打赏）
 * @Version: 1.0
 */
@AllArgsConstructor
public enum ActionEnum {
    LIKE(),
    COMMENT(),
    SHARE(),
    GIFT();
}

package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-14  09:05
 * @Description: 行为枚举（点赞、评论、打赏）
 * @Version: 1.0
 */
@AllArgsConstructor
public enum ActionEnum {
    LIKE(1, "点赞"),
    COMMENT(2, "评论"),
    GIFT(3, "打赏");

    @Getter
    private int code;
    @Getter
    private String msg;
}

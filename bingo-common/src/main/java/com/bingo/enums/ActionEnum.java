package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-14  09:05
 * @Description: 行为枚举（点赞、评论、转发、打赏）
 * @Version: 1.0
 */
@AllArgsConstructor
public enum ActionEnum {
    LIKE(0, "点赞行为")
//    COMMENT(),
//    SHARE(),
//    GIFT();
    ;

    @Getter
    private int code;
    @Getter
    private String msg;
}

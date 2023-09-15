package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-15  17:50
 * @Description: 目标对象枚举（用户、帖子、评论等...）
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum TargetEnum {
    USER(1, "用户"),
    POST(2, "帖子"),
    TOPIC(3,"主题")
    ;

    private int code;
    private String msg;
}

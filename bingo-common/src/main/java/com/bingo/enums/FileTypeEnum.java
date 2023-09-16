package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 徐志斌
 * @Date: 2023/9/16 20:17
 * @Version 1.0
 * @Description: 文件类型枚举
 */
@Getter
@AllArgsConstructor
public enum FileTypeEnum {
    AVATAR(1, "头像"),
    AUDIO(2, "语音"),
    TREND(3, "社区动态")
    ;

    private int code;
    private String msg;
}

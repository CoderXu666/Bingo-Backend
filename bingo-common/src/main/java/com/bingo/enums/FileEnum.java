package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author 徐志斌
 * @Date: 2023/9/16 20:17
 * @Version 1.0
 * @Description: 对象存储——文件类型枚举
 */
@Getter
@AllArgsConstructor
public enum FileEnum {
    AVATAR(1, "头像图片"),
    VOICE(2, "语音WAV文件"),
    DYNAMIC(3, "社区动态图片"),
    TOPIC(4, "话题图片"),
    COMMENT(5, "评论图片");

    private int code;
    private String msg;
}

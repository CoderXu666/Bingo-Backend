package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-14  09:05
 * @Description: 聊天内容类型枚举
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum ChatRecordEnum {
    FONT(1, "文字"),
    SOUND(2, "音频"),
    PICTURE(3, "图片"),
    VIDEO(4, "视频"),
    FILE(5, "文件"),
    GIFT(6, "礼物"),
    LINE(7, "视频通话");

    private int code;
    private String msg;
}

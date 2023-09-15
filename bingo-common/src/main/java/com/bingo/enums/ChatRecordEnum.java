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
    FONT(1, "文字内容"),
    SOUND(2, "音频内容"),
    PICTURE(3, "图片内容"),
    VIDEO(4, "视频内容"),
    FILE(5, "文件内容"),
    GIFT(6, "礼物打赏"),
    LINE(7, "视频通话");

    private int code;
    private String msg;
}

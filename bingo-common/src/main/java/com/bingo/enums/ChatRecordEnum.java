package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-14  09:05
 * @Description: 聊天内容类型枚举
 * @Version: 1.0
 */
@AllArgsConstructor
public enum ChatRecordEnum {
    FONT(1, "文字内容"),
    SOUND(2, "音频内容"),
    PICTURE(3, "图片内容"),
    FILE(4, "文件内容"),
    GIFT(5, "礼物打赏"),
    LINE(6, "视频通话");

    @Getter
    private int code;
    @Getter
    private String msg;
}

package com.bingo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-27  15:10
 * @Description: Minio对象存储枚举
 * @Version: 1.0
 */
@Getter
@AllArgsConstructor
public enum MinioEnum {
    CHAT_VOICE_BUCKET("voice-bucket", "聊天语音消息文件存储Bucket");

    private String bucketName;
    private String desc;
}

package com.bingo.strategy.chat;

import com.bingo.enums.ChatRecordEnum;
import com.bingo.enums.ResponseEnum;
import com.bingo.exception.BingoException;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.utils.MinioUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  17:38
 * @Description: 语音策略Handler
 * @Version: 1.0
 */
@Slf4j
@Component
public class ChatVoiceHandler extends AbstractChatStrategy {
    @Autowired
    private MinioUtil minioUtil;

    /**
     * 当前策略枚举
     */
    @Override
    ChatRecordEnum getEnum() {
        return ChatRecordEnum.VOICE;
    }

    /**
     * 上传音频数据
     */
    @Override
    public Boolean handleChatRecord(BingoChatSendRecord sendRecord, MultipartFile file) {
        if (ObjectUtils.isEmpty(file)) {
            throw new BingoException(ResponseEnum.FILE_NOT_EXIST);
        }
        return null;
    }
}

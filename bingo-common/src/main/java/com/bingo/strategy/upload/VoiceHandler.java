package com.bingo.strategy.upload;

import com.bingo.constant.MinioConstant;
import com.bingo.enums.FileEnum;
import com.bingo.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 徐志斌
 * @Date: 2023/9/17 18:23
 * @Version 1.0
 * @Description: 语音文件Handler
 */
public class VoiceHandler extends AbstractUploadStrategy {
    @Autowired
    private MinioUtil minioUtil;

    @Override
    FileEnum getEnum() {
        return FileEnum.VOICE;
    }

    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        return minioUtil.upload(file, MinioConstant.CHAT_VOICE_BUCKET);
    }

    @Override
    public Boolean removeFile(String bucketName, String objName) {
        return null;
    }
}

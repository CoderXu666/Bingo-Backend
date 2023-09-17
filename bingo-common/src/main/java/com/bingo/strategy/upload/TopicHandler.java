package com.bingo.strategy.upload;

import com.bingo.enums.FileEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 徐志斌
 * @Date: 2023/9/17 18:23
 * @Version 1.0
 * @Description: 话题文件文件Handler
 */
public class TopicHandler extends AbstractUploadStrategy {

    @Override
    FileEnum getEnum() {
        return FileEnum.TOPIC;
    }

    @Override
    public String uploadFile(MultipartFile file) {
        return null;
    }

    @Override
    public Boolean removeFile(String bucketName, String objName) {
        return null;
    }
}

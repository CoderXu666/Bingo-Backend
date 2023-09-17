package com.bingo.strategy.upload;

import com.bingo.enums.FileEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 徐志斌
 * @Date: 2023/9/17 18:23
 * @Version 1.0
 * @Description: 评论图片文件Handler
 */
public class CommentHandler extends AbstractUploadStrategy {

    @Override
    FileEnum getEnum() {
        return FileEnum.COMMENT;
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

package com.bingo.strategy.uploadfile;

import com.bingo.enums.FileEnum;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 徐志斌
 * @Date: 2023/9/17 18:15
 * @Version 1.0
 * @Description: 文件上传策略类
 */
public abstract class AbstractUploadStrategy {
    /**
     * 获取策略类型枚举
     */
    abstract FileEnum getEnum();

    /**
     * 上传文件
     */
    public abstract Boolean uploadFile(MultipartFile file);

    /**
     * 移除文件
     */
    public abstract Boolean removeFile();
}

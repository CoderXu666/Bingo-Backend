package com.bingo.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.bingo.pojo.constant.ConfigConstant;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/2/27
 * @description: 阿里云OSS图片上传工具类
 * @Version: 1.0
 */
public class FileUploadUtil {
    /**
     * 上传图片（头像、表情包等...）
     *
     * @param file 图片文件
     * @param type 图片目录：例如头像avatar，表情包emoji
     * @return
     */
    public static String uploadFile(MultipartFile file, String type) {
        // 初始化OSS参数
        String endPoint = ConfigConstant.END_POINT;
        String accessKeyId = ConfigConstant.ACCESS_KEY_ID;
        String accessKeySecret = ConfigConstant.ACCESS_KEY_SECRET;
        String bucketName = ConfigConstant.BUCKET_NAME;

        try {
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            InputStream inputStream = file.getInputStream();
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
            String fileName = uuid + file.getOriginalFilename();
            fileName = type + "/" + fileName;
            ossClient.putObject(bucketName, fileName, inputStream);
            ossClient.shutdown();
            String url = "https://" + bucketName + "." + endPoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
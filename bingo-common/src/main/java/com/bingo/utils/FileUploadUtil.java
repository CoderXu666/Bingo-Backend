package com.bingo.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.bingo.constant.ConfigConstant;
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
            // 创建OSSClient实例
            OSS ossClient = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
            // 获取上传文件输入流
            InputStream inputStream = file.getInputStream();
            // 获取文件名称：文件名称添加随即唯一值，防止上传相同图片会进行覆盖
            String uuid = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 5);
            String fileName = uuid + file.getOriginalFilename();
            // 添加文件夹目录
            fileName = type + "/" + fileName;
            // 进行上传
            ossClient.putObject(bucketName, fileName, inputStream);
            // 关闭OSSClient
            ossClient.shutdown();
            // 图片url
            String url = "https://" + bucketName + "." + endPoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
//package com.bingo.utils;
//
//import com.aliyun.vod.upload.impl.UploadVideoImpl;
//import com.aliyun.vod.upload.req.UploadStreamRequest;
//import com.aliyun.vod.upload.resp.UploadStreamResponse;
//import com.bingo.constant.ConfigConstant;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.InputStream;
//
///**
// * @Author: 徐志斌
// * @CreateDate: 2023/2/28
// * @description: 视频点播工具类
// * @Version: 1.0
// */
//public class VideoUtil {
//    /**
//     * 上传视频
//     */
//    public static String uploadVideo(MultipartFile file) {
//        try {
//            //上传文件的原始名称
//            String fileName = file.getOriginalFilename();
//            //上传后显示的名称，这里我想去掉后缀名，例如.mp4
//            String title = fileName.substring(0, fileName.lastIndexOf("."));
//            //文件输入流
//            InputStream inputStream = file.getInputStream();
//            UploadStreamRequest request =
//                    new UploadStreamRequest(
//                            ConfigConstant.ACCESS_KEY_ID,
//                            ConfigConstant.ACCESS_KEY_SECRET,
//                            title, fileName, inputStream);
//            UploadVideoImpl uploader = new UploadVideoImpl();
//            UploadStreamResponse response = uploader.uploadStream(request);
//            //获取到视频id（这个很重要，有了视频id从才可以获取播放地址和凭证）
//            return response.getVideoId();
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
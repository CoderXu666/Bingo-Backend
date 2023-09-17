package com.bingo.controller;

import com.bingo.constant.MinioConstant;
import com.bingo.enums.ResponseEnum;
import com.bingo.response.R;
import com.bingo.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author 徐志斌
 * @Date: 2023/9/17 18:04
 * @Version 1.0
 * @Description: 聊天模块相关文件处理
 */
@RestController
public class BingoChatFileController {
    @Autowired
    private MinioUtil minioUtil;

    /**
     * 上传头像
     * -------------------------------------------------------------------------------
     * 访问URL例子：http://101.42.13.186:9000/avatar-bucket/1687483809516_1690621557315.jpg
     */
    @PostMapping("/upload_file")
    public R uploadAvatar(MultipartFile file) throws Exception {
        String avatarUrl = minioUtil.upload(file, MinioConstant.USER_AVATAR_BUCKET);
        return R.out(ResponseEnum.SUCCESS, avatarUrl);
    }
}

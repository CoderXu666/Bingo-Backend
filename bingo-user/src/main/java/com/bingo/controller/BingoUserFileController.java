package com.bingo.controller;

import com.bingo.constant.MinioConstant;
import com.bingo.enums.ResponseEnum;
import com.bingo.response.R;
import com.bingo.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-07  16:58
 * @Description: 用户模块-文件对象存储相关操作
 * @Version: 1.0
 */
public class BingoUserFileController {
    @Autowired
    private MinioUtil minioUtil;

    /**
     * 上传头像
     * -------------------------------------------------------------------------------
     * 访问URL例子：http://101.42.13.186:9000/avatar-bucket/1687483809516_1690621557315.jpg
     */
    @PostMapping("/upload_avatar")
    public R uploadAvatar(MultipartFile file) {
        String avatarUrl = minioUtil.upload(file, MinioConstant.AVATAR_BUCKET);
        return R.out(ResponseEnum.SUCCESS, avatarUrl);
    }

    /**
     * 移除头像
     */
    @DeleteMapping("/remove_avatar")
    public R removeAvatar(String objectName) throws Exception {
        minioUtil.removeObject("avatar-bucket", objectName);
        return R.out(ResponseEnum.SUCCESS, null);
    }
}

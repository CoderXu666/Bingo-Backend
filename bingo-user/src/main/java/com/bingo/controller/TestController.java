package com.bingo.controller;

import com.bingo.utils.FileUploadUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/3/1
 * @description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping("/upload")
    public String test(MultipartFile file, String type) {
        String s = FileUploadUtil.uploadFile(file, type);
        return s;
    }
}
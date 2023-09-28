package com.bingo.controller;

import com.bingo.annotation.RateLimiter;
import com.bingo.enums.MinioEnum;
import com.bingo.enums.ResponseEnum;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.response.R;
import com.bingo.service.ChatService;
import com.bingo.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/chat")
public class BingoChatSendController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private MinioUtil minioUtil;

    /**
     * 发送消息（单聊）
     */
    @PostMapping("/send_one")
    @RateLimiter(time = 3, count = 6)
    public R sendChatByUid(@RequestBody ChatRecordDTO msgDTO, @RequestParam("file") MultipartFile file) throws Exception {
        chatService.sendChatRecord(msgDTO, file);
        return R.out(ResponseEnum.SUCCESS, null);
    }

    // 测试接收语音文件
    @RequestMapping("/test")
    public R test(MultipartFile file) throws Exception {
        String uploadUrl = minioUtil.upload(file, MinioEnum.CHAT_VOICE_BUCKET.getBucketName());
        return R.out(ResponseEnum.SUCCESS, null);
    }
}

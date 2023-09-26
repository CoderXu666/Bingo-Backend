package com.bingo.controller;

import com.bingo.annotation.RateLimiter;
import com.bingo.constant.MinioConstant;
import com.bingo.enums.ResponseEnum;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.response.R;
import com.bingo.service.ChatService;
import com.bingo.utils.MinioUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
     * TODO 想办法，能不能将数据封装到msgDTO
     */
    @PostMapping("/send_one")
    @RateLimiter(time = 3, count = 6)
    public R sendChatByUid(@RequestBody ChatRecordDTO msgDTO) throws Exception {
        chatService.sendChatRecord(msgDTO);
        return R.out(ResponseEnum.SUCCESS, null);
    }

    // 测试接收语音文件
    @RequestMapping("/test")
    public R test(MultipartFile file) throws Exception {
        System.out.println(file);
        minioUtil.upload(file, MinioConstant.CHAT_VOICE_BUCKET);
        return R.out(ResponseEnum.SUCCESS, null);
    }
}

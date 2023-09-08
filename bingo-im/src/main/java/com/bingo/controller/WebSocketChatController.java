package com.bingo.controller;

import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.response.R;
import com.bingo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class WebSocketChatController {
    @Autowired
    private ChatService chatService;

    /**
     * 发送消息（单聊）
     */
    @PostMapping("/send_one")
//    @RateLimiter(time = 3, count = 8)
    public R sendChatByUid(@RequestBody ChatRecordDTO msgDTO) throws Exception {
        chatService.sendChatRecord(msgDTO);
        return R.out(RespCodeEnum.SUCCESS, "发送成功");
    }
}

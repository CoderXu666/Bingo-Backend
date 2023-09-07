package com.bingo.controller;

import com.bingo.annotation.RateLimiter;
import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.common.response.R;
import com.bingo.pojo.dto.im.ChatMsgDTO;
import com.bingo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    /**
     * 发送消息（单聊）
     */
    @PostMapping("/send_msg")
//    @RateLimiter(time = 3, count = 8)
    public R sendChatByUid(@RequestBody ChatMsgDTO msgDTO) throws Exception {
        chatService.sendChatByUid(msgDTO);
        return R.out(RespCodeEnum.SUCCESS, "发送成功");
    }
}

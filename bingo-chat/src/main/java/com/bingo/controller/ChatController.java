package com.bingo.controller;

import com.bingo.ChatMsgDTO;
import com.bingo.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private SendService pushService;

    /**
     * 消息推送给所有用户
     */
    @PostMapping("/pushAll")
    public void pushToAll(@RequestParam("msg") String msg) {
        pushService.sendMsgToAll(msg);
    }

    /**
     * 消息推送给指定用户
     */
    @PostMapping("/send_one")
    public void sendMsgByUserId(@RequestBody ChatMsgDTO msgDTO) {
        pushService.sendMsgByUserId(msgDTO.getUserId(), msgDTO.getMsg());
    }
}

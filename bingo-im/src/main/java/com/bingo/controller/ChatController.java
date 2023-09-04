package com.bingo.controller;

import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.common.response.R;
import com.bingo.pojo.dto.im.ChatMsgDTO;
import com.bingo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    /**
     * 消息推送给指定uid用户
     */
    @PostMapping("/send_msg")
    public R sendMsgByUserId(@RequestBody ChatMsgDTO msgDTO) throws Exception {
        chatService.sendMsgByUserId(msgDTO);
        return R.out(RespCodeEnum.SUCCESS, "发送消息成功");
    }

    /**
     * 消息推送给所有用户
     */
    @PostMapping("/send_all")
    public R pushToAll(@RequestParam("msg") String msg) {
        chatService.sendMsgToAll(msg);
        return R.out(RespCodeEnum.SUCCESS, "消息群发成功");
    }
}

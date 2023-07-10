package com.bingo.controller;

import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.ChatMsgDTO;
import com.bingo.pojo.resp.R;
import com.bingo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;

    /**
     * 消息推送给所有用户
     */
    @PostMapping("/push_all")
    public R pushToAll(@RequestParam("msg") String msg) {
        chatService.sendMsgToAll(msg);
        return R.out(RespCodeEnum.SUCCESS, "消息群发成功");
    }

    /**
     * 消息推送给指定用户
     */
    @PostMapping("/send_one")
    public R sendMsgByUserId(@RequestBody ChatMsgDTO msgDTO) throws Exception {
        chatService.sendMsgByUserId(msgDTO.getUserId(), msgDTO.getMsg());
        return R.out(RespCodeEnum.SUCCESS, "发送消息成功");
    }
}

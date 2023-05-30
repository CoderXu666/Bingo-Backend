package com.bingo.controller;

import com.bingo.pojo.dto.ChatMsgDTO;
import com.bingo.enums.RespCodeEnum;
import com.bingo.resp.R;
import com.bingo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private ChatService pushService;

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
    public R sendMsgByUserId(@RequestBody ChatMsgDTO msgDTO) {
        try {
            pushService.sendMsgByUserId(msgDTO.getUserId(), msgDTO.getMsg());
            return R.out(RespCodeEnum.SUCCESS, "发送消息成功");
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, e.getMessage());
        }
    }
}

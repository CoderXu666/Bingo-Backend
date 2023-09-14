package com.bingo.controller;

import com.bingo.annotation.RateLimiter;
import com.bingo.enums.ResponseEnum;
import com.bingo.netty.NettyChannelRelation;
import com.bingo.pojo.dto.im.ChatRecordDTO;
import com.bingo.response.R;
import com.bingo.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/chat")
public class WebSocketChatController {
    @Autowired
    private ChatService chatService;

    /**
     * 发送消息（单聊）
     */
    @PostMapping("/send_one")
    @RateLimiter(time = 3, count = 6)
    public R sendChatByUid(@RequestBody ChatRecordDTO msgDTO) throws Exception {
        chatService.sendChatRecord(msgDTO);
        return R.out(ResponseEnum.SUCCESS, null);
    }

    /**
     * 查询当前WebSocket连接
     */
    @RequestMapping("/search_ws_connect")
    public Map<String, Object> test() {
        Map<String, Object> map = new HashMap<>();
        map.put("Channel Group", NettyChannelRelation.getChannelGroup());
        map.put("uid Channel映射", NettyChannelRelation.getUserChannelMap());
        return map;
    }
}

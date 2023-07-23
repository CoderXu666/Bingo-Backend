package com.bingo.service;

import com.bingo.pojo.dto.ChatMsgDTO;

public interface ChatService {
    void sendMsgByUserId(Long userId, String msg) throws Exception;

    void sendMsgToAll(String msg);

    void sendMsg2Group(ChatMsgDTO msgDTO);
}

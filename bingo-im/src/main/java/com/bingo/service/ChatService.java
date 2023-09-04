package com.bingo.service;

import com.bingo.pojo.dto.im.ChatMsgDTO;

public interface ChatService {
    void sendMsgByUserId(ChatMsgDTO msgDTO) throws Exception;

    void sendMsgToAll(String msg);

}

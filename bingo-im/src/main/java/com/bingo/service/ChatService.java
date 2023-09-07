package com.bingo.service;

import com.bingo.pojo.dto.im.ChatRecordDTO;

public interface ChatService {
    void sendChatByUid(ChatRecordDTO msgDTO) throws Exception;
}

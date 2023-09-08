package com.bingo.service;

import com.bingo.pojo.dto.im.ChatRecordDTO;

public interface ChatService {
    void sendChatRecord(ChatRecordDTO msgDTO) throws Exception;
}

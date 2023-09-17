package com.bingo.service;

import com.bingo.pojo.dto.im.ChatRecordDTO;
import org.springframework.web.multipart.MultipartFile;

public interface ChatService {
    void sendChatRecord(ChatRecordDTO msgDTO, MultipartFile file) throws Exception;
}

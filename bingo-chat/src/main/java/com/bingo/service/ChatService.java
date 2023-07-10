package com.bingo.service;

public interface ChatService {
    void sendMsgByUserId(Long userId, String msg) throws Exception;

    void sendMsgToAll(String msg);
}

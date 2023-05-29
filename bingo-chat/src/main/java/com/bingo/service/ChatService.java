package com.bingo.service;

public interface ChatService {
    void sendMsgByUserId(String userId, String msg) throws Exception;

    void sendMsgToAll(String msg);
}

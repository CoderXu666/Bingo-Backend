package com.bingo.service;

public interface SendService {
    void sendMsgByUserId(String userId, String msg);

    void sendMsgToAll(String msg);
}

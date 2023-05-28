package com.bingo.service;

public interface SendService {
    void sendMsgToOne(String userId, String msg);

    void sendMsgToAll(String msg);
}

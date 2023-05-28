package com.bingo.controller;

import com.bingo.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/push")
public class TestController {

    @Autowired
    private SendService pushService;

    /**
     * 推送给所有用户
     */
    @PostMapping("/pushAll")
    public void pushToAll(@RequestParam("msg") String msg) {
        pushService.sendMsgToAll(msg);
    }

    /**
     * 推送给指定用户
     */
    @PostMapping("/pushOne")
    public void pushMsgToOne(@RequestParam("userId") String userId, @RequestParam("msg") String msg) {
        pushService.sendMsgToOne(userId, msg);
    }

}

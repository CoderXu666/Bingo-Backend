package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.FriendChatDTO;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoFriendChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-16
 */
@RestController
@RequestMapping("/friend_chat")
public class BingoFriendChatController {

    @Autowired
    private BingoFriendChatService friendChatService;

    /**
     * 好友发送消息
     */
    @PostMapping("/save")
    public R saveFriendChat(@RequestBody FriendChatDTO friendChatDTO) throws Exception {
        friendChatService.saveFriendChat(friendChatDTO);
        return R.out(RespCodeEnum.SUCCESS, "创建成功");
    }

    /**
     * 撤销消息
     */
    @PostMapping("/recall")
    public R recallMessage(@RequestBody FriendChatDTO friendChatDTO) throws Exception {
        friendChatService.recallMessage(friendChatDTO);
        return R.out(RespCodeEnum.SUCCESS, "撤销成功");
    }
}


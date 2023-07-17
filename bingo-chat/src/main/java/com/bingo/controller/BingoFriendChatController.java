package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.FriendChatDTO;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoFriendChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 周英俊
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
    public R saveFriendChat(@RequestBody FriendChatDTO friendChatDTO) {
        friendChatService.saveFriendChat(friendChatDTO);
        return R.out(RespCodeEnum.SUCCESS, "创建成功");
    }

    /**
     * 删除好友消息
     */
    @DeleteMapping("/delete")
    public R deleteFriendChat(Long id) {
        friendChatService.deleteFriendChat(id);
        return R.out(RespCodeEnum.SUCCESS, "删除成功");
    }



}


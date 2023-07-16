package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.dto.GroupChatDTO;
import com.bingo.pojo.resp.R;
import com.bingo.service.BingoGroupChatService;
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
 * @author 周英俊
 * @since 2023-07-16
 */
@RestController
@RequestMapping("/friend_chat")
public class BingoFriendChatController {

    @Autowired
    private BingoGroupChatService groupChatService;

    /**
     * 发送消息
     */
    @PostMapping("/save")
    public R saveGroup(@RequestBody GroupChatDTO groupChatDTO) {
        groupChatService.saveGroup(groupChatDTO);
        return R.out(RespCodeEnum.SUCCESS, "创建成功");
    }


}


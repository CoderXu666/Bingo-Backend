package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.resp.R;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.service.BingoChatFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-27
 */
@RestController
@RequestMapping("/relation")
public class BingoChatFriendController {
    @Autowired
    private BingoChatFriendService relationService;

    /**
     * 根据 user_id 查询当前用户的好友列表，顺带查询聊天记录
     */
    @GetMapping("/list")
    public R getListById(Long id) {
        List<BingoUserVO> friendList = relationService.getListById(id);
        return R.out(RespCodeEnum.SUCCESS, friendList);
    }

    /**
     * 删除当前用户的好友
     */
    @DeleteMapping("/delete")
    public R deleteById(Long userId, Long friendId) throws Exception {
        relationService.deleteById(userId, friendId);
        return R.out(RespCodeEnum.SUCCESS, "操作成功");
    }
}


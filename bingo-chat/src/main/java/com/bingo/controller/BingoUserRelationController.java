package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.resp.R;
import com.bingo.service.BingoUserRelationService;
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
public class BingoUserRelationController {
    @Autowired
    private BingoUserRelationService relationService;

    /**
     * 查询当前用户的好友列表
     */
    @GetMapping("/list")
    public R findFriendById(Long id) {
        try {
            List<BingoUserVO> friendList = relationService.findFriend(id);
            return R.out(RespCodeEnum.SUCCESS, friendList);
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, e.getMessage());
        }
    }

    /**
     * 删除当前用户的好友
     */
    @DeleteMapping("/delete")
    public R deleteById(Long userId, Long friendId) {
        try {
            relationService.deleteById(userId, friendId);
            return R.out(RespCodeEnum.SUCCESS, "操作成功");
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }


}


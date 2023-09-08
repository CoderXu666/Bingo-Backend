package com.bingo.controller;

import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.user.BingoGift;
import com.bingo.response.R;
import com.bingo.service.BingoGiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-08  16:46
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("/gift")
public class BingoGiftController {
    @Autowired
    private BingoGiftService giftService;

    /**
     * 礼品权益概览
     */
    @GetMapping("/list")
    public R list() {
        List<BingoGift> list = giftService.getList();
        return R.out(RespCodeEnum.SUCCESS, list);
    }
}

package com.bingo.controller;


import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.po.BingoGoods;
import com.bingo.resp.R;
import com.bingo.service.BingoGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @since 2023-06-09
 */
@RestController
@RequestMapping("/goods")
public class BingoGoodsController {
    @Autowired
    private BingoGoodsService goodsService;

    /**
     * 查询商品列表
     */
    @GetMapping("/list")
    public R queryList() {
        try {
            List<BingoGoods> list = goodsService.queryList();
            return R.out(RespCodeEnum.SUCCESS, list);
        } catch (Exception e) {
            return R.out(RespCodeEnum.FAIL, "操作失败");
        }
    }
}


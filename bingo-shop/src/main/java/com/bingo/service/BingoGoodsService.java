package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoGoods;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-09
 */
public interface BingoGoodsService extends IService<BingoGoods> {
    List<BingoGoods> queryList();
}
package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.user.BingoGift;

import java.util.List;

/**
 * <p>
 * 礼物权益表 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-09-08
 */
public interface BingoGiftService extends IService<BingoGift> {

    List<BingoGift> getList();
}

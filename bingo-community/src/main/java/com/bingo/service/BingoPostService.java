package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoPost;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-04
 */
public interface BingoPostService extends IService<BingoPost> {

    boolean saveInfo(BingoPost bingoPost);
}

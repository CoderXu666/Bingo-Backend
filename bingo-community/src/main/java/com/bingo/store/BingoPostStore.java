package com.bingo.store;

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
public interface BingoPostStore extends IService<BingoPost> {

    boolean savePost(BingoPost bingoPost);
}

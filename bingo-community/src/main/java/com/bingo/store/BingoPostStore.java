package com.bingo.store;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.common.PageParam;
import com.bingo.pojo.po.community.BingoPost;

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
    Page<BingoPost> pagePost(PageParam pageParam);
}

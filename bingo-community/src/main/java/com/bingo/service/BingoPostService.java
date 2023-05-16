package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.BingoPostDTO;
import com.bingo.pojo.po.BingoPost;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-13
 */
public interface BingoPostService extends IService<BingoPost> {
    /**
     * 发布帖子
     */
    Boolean savePost(BingoPostDTO postDTO);
}

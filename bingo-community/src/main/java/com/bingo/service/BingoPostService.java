package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.LikeDTO;
import com.bingo.pojo.dto.PostDTO;
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
    Boolean savePost(PostDTO postDTO);

    Boolean likePost(LikeDTO likeDTO);
}

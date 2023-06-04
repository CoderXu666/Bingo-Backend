package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.LikeDTO;
import com.bingo.pojo.dto.PostDTO;
import com.bingo.pojo.po.BingoPost;
import com.bingo.pojo.vo.PostVO;

import java.io.IOException;
import java.util.List;

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
    List<PostVO> searchPost(String content) throws IOException;
}

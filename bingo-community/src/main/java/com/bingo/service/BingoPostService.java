package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.common.PageParam;
import com.bingo.pojo.dto.community.LikeDTO;
import com.bingo.pojo.dto.community.PostDTO;
import com.bingo.pojo.dto.SearchDTO;
import com.bingo.pojo.po.community.BingoPost;
import com.bingo.pojo.vo.community.PostPageVO;
import com.bingo.pojo.vo.community.PostVO;

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
    List<PostVO> searchPost(SearchDTO searchDTO) throws IOException;
    PostPageVO pagePost(PageParam pageParam);
}

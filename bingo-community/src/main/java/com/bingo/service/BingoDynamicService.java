package com.bingo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.PageParam;
import com.bingo.pojo.dto.community.LikeDTO;
import com.bingo.pojo.dto.community.PostDTO;
import com.bingo.pojo.dto.SearchDTO;
import com.bingo.pojo.po.community.BingoDynamic;
import com.bingo.pojo.resp.community.PostPageResp;
import com.bingo.pojo.resp.community.PostResp;

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
public interface BingoDynamicService extends IService<BingoDynamic> {
    Boolean saveDynamic(PostDTO postDTO);
    Boolean likeDynamic(LikeDTO likeDTO);
    List<PostResp> searchDynamic(SearchDTO searchDTO) throws IOException;
    PostPageResp getList(PageParam pageParam);
}

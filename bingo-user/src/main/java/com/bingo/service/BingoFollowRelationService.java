package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoFollowRelation;
import com.bingo.pojo.vo.BingoFollowVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-09
 */
public interface BingoFollowRelationService extends IService<BingoFollowRelation> {

    Boolean followUser(Long userId1, Long userId2);

    List<BingoFollowVO> findFollowList(Long userId);
}

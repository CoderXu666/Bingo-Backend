package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoFollowRelation;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-09
 */
public interface BingoFollowRelationStore extends IService<BingoFollowRelation> {

    Boolean saveFollow(BingoFollowRelation bingoFollowRelation);

    List<BingoFollowRelation> findFollowList(Long userId1);

    BingoFollowRelation getOneFollow(Long userId1, Long userId2);
}

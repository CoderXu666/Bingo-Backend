package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFollowRelationMapper;
import com.bingo.pojo.po.BingoFollowRelation;
import com.bingo.service.BingoFollowRelationService;
import com.bingo.store.BingoFollowRelationStore;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 周英俊
 * @since 2023-07-09
 */
@Service
public class BingoFollowRelationServiceImpl extends ServiceImpl<BingoFollowRelationMapper, BingoFollowRelation> implements BingoFollowRelationService {

    @Autowired
    private BingoFollowRelationStore followRelationStore;

    /**
     * 关注/取消用户
     */
    @Override
    public Boolean followUser(Long userId1, Long userId2) {
        BingoFollowRelation followRelation = followRelationStore.getOneFollow(userId1, userId1);
        // 没关注过，进行关注
        if (ObjectUtils.isEmpty(followRelation)) {
            followRelationStore.saveFollow(null);
        }
        // TODO 关注过了，是什么状态
        BingoFollowRelation bingoFollowRelation = new BingoFollowRelation();
        bingoFollowRelation.setUserId1(userId1);
        bingoFollowRelation.setUserId2(userId2);
        return followRelationStore.saveFollow(bingoFollowRelation);
    }

    /**
     * 查询用户的关注
     */
    @Override
    public List<BingoFollowRelation> findFollowList(Long userId) {
        List<BingoFollowRelation> followRelation = followRelationStore.findFollowList(userId);
        List<Long> ids = new ArrayList<>();
        // TODO 用户信息
        for (BingoFollowRelation relation : followRelation) {
            ids.add(relation.getUserId2());
        }
        return followRelation;
    }
}

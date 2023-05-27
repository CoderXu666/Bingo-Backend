package com.bingo.store.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoUserRelationMapper;
import com.bingo.pojo.po.BingoUserRelation;
import com.bingo.store.BingoUserRelationStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-27
 */
@Service
public class BingoUserRelationStoreImpl extends ServiceImpl<BingoUserRelationMapper, BingoUserRelation> implements BingoUserRelationStore {

    /**
     * 查询当前用户的好友id集合
     */
    @Override
    public List<Long> findFriend(Long id) {
        List<Long> ids = new ArrayList<>();
        QueryWrapper<BingoUserRelation> queryWrapper1 = new QueryWrapper();
        queryWrapper1.eq("user1", id);
        List<BingoUserRelation> list1 = this.list(queryWrapper1);
        for (BingoUserRelation userRelation : list1) {
            Long userId2 = userRelation.getUserId2();
            ids.add(userId2);
        }
        QueryWrapper<BingoUserRelation> queryWrapper2 = new QueryWrapper();
        queryWrapper2.eq("user2", id);
        List<BingoUserRelation> list2 = this.list(queryWrapper2);
        for (BingoUserRelation userRelation : list2) {
            Long userId1 = userRelation.getUserId1();
            ids.add(userId1);
        }
        return ids;
    }
}

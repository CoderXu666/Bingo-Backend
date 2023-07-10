package com.bingo.store.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFollowRelationMapper;
import com.bingo.pojo.po.BingoFollowRelation;
import com.bingo.store.BingoFollowRelationStore;
import org.springframework.stereotype.Service;

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
public class BingoFollowRelationStoreImpl extends ServiceImpl<BingoFollowRelationMapper, BingoFollowRelation> implements BingoFollowRelationStore {

    /**
     * 关注用户
     */
    @Override
    public Boolean saveFollow(BingoFollowRelation bingoFollowRelation) {
        return this.save(bingoFollowRelation);
    }

    /**
     * 查询用户的关注列表
     */
    @Override
    public List<BingoFollowRelation> findFollowList(Long userId1) {
        QueryWrapper<BingoFollowRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id1", userId1);
        queryWrapper.orderByDesc("create_time");
        return this.list(queryWrapper);
    }

    /**
     * 查询是否关注该用户
     */
    @Override
    public BingoFollowRelation getOneFollow(Long userId1, Long userId2) {
        QueryWrapper<BingoFollowRelation> queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id1", userId1);
        queryWrapper.eq("user_id2", userId2);
        return this.getOne(queryWrapper);
    }

    /**
     * 关注、取消关注
     */
    @Override
    public Boolean updateFollow(BingoFollowRelation bingoFollowRelation) {
        return this.updateById(bingoFollowRelation);
    }
}

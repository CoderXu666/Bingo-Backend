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

    @Override
    public List<BingoFollowRelation> findFollow(Long userId1) {
        QueryWrapper<BingoFollowRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id1", userId1);
        List<BingoFollowRelation> followRelation = this.list(queryWrapper);
        return followRelation;
    }

    @Override
    public BingoFollowRelation findFollowOne(Long userId1, Long userId2) {
        QueryWrapper<BingoFollowRelation> queryWrapper = new QueryWrapper();
        queryWrapper.eq("uesr_id1", userId1);
        queryWrapper.eq("uesr_id2", userId2);
        BingoFollowRelation followRelation = this.getOne(queryWrapper);
        return followRelation;
    }
}

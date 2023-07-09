package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFollowRelationMapper;
import com.bingo.pojo.po.BingoFollowRelation;
import com.bingo.service.BingoFollowRelationService;
import com.bingo.store.BingoFollowRelationStore;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
     * 关注用户
     */
    @Override
    public Boolean saveFollow(Long userId1, Long userId2) throws Exception {
        BingoFollowRelation bingoFollowRelation = new BingoFollowRelation();
        bingoFollowRelation.setUserId1(userId1);
        bingoFollowRelation.setUserId2(userId2);
        bingoFollowRelation.setCreateTime(new Date());
        BingoFollowRelation followOne = followRelationStore.findFollowOne(userId1, userId1);
        if (!ObjectUtils.isEmpty(followOne)) {
            throw new Exception("您已关注用户");
        }
        return followRelationStore.saveFollow(bingoFollowRelation);
    }

    /**
     * 查询用户的关注
     */
    @Override
    public List<BingoFollowRelation> findFollow(Long userId1) {
        List<BingoFollowRelation> followRelation = followRelationStore.findFollow(userId1);
        return followRelation;
    }

}

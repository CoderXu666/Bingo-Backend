package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFollowRelationMapper;
import com.bingo.pojo.po.BingoFollowRelation;
import com.bingo.pojo.po.BingoUser;
import com.bingo.pojo.vo.BingoFollowVO;
import com.bingo.service.BingoFollowRelationService;
import com.bingo.store.BingoFollowRelationStore;
import com.bingo.store.BingoUserStore;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-09
 */
@Service
public class BingoFollowRelationServiceImpl extends ServiceImpl<BingoFollowRelationMapper, BingoFollowRelation> implements BingoFollowRelationService {

    @Autowired
    private BingoFollowRelationStore followRelationStore;
    @Autowired
    private BingoUserStore userStore;

    /**
     * 关注/取消用户
     */
    @Override
    public Boolean followUser(Long userId1, Long userId2) {
        //查询是否关注过
        BingoFollowRelation followRelation = followRelationStore.getOneFollow(userId1, userId2);
        BingoFollowRelation bingoFollowRelation = new BingoFollowRelation();
        bingoFollowRelation.setUserId1(userId1);
        bingoFollowRelation.setUserId2(userId2);
        // 没关注过
        if (ObjectUtils.isEmpty(followRelation)) {
            return followRelationStore.saveFollow(bingoFollowRelation);
        }
        Long id = followRelation.getId();
        bingoFollowRelation.setId(id);
        bingoFollowRelation.setUpdateTime(new Date());
        // TODO 已经关注了、再点取消关注
        if (followRelation.getFollowMark()) {
            bingoFollowRelation.setFollowMark(false);
        } else {
            // TODO 曾经关注过了、（取关状态）
            bingoFollowRelation.setFollowMark(true);
        }
        return followRelationStore.updateFollow(bingoFollowRelation);
    }

    /**
     * 查询用户的关注
     */
    @Override
    public List<BingoFollowVO> findFollowList(Long userId) {
        List<BingoFollowRelation> followRelation = followRelationStore.findFollowList(userId);
        List<Long> ids = new ArrayList<>();
        // TODO 用户信息
        for (BingoFollowRelation relation : followRelation) {
            ids.add(relation.getUserId2());
        }
        List<BingoUser> userList = userStore.getUserListByIds(ids);
        List<BingoFollowVO> followVOList = new ArrayList<>();
        for (BingoUser bingoUser : userList) {
            BingoFollowVO followVO = new BingoFollowVO();
            BeanUtils.copyProperties(bingoUser, followVO);
            followVOList.add(followVO);
        }
        return followVOList;
    }
}

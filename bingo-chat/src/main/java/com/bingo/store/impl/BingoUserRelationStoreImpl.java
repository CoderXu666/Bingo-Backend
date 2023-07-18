package com.bingo.store.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoUserRelationMapper;
import com.bingo.pojo.po.BingoUserRelation;
import com.bingo.store.BingoUserRelationStore;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
     * 根据user_id查询当前用户的好友
     */
    @Override
    public List<Long> getListById(Long id) {
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

    /**
     * 删除当前用户好友id
     */
    @Override
    public Boolean deleteById(Long userId, Long friendId) throws Exception {
        BingoUserRelation relation = null;
        List<Long> friendList = this.getListById(userId);
        for (Long id : friendList) {
            if (friendId.equals(id)) {
                QueryWrapper<BingoUserRelation> wrapper1 = new QueryWrapper();
                wrapper1.eq("user1", friendId);
                wrapper1.eq("user2", userId);
                BingoUserRelation userInfo1 = this.getOne(wrapper1);
                QueryWrapper<BingoUserRelation> wrapper2 = new QueryWrapper();
                wrapper2.eq("user1", userId);
                wrapper2.eq("user2", friendId);
                BingoUserRelation userInfo2 = this.getOne(wrapper2);
                if (ObjectUtils.isEmpty(userInfo1)) {
                    relation = userInfo2;
                } else {
                    relation = userInfo1;
                }
            }
        }
        if (ObjectUtils.isEmpty(relation)) {
            throw new Exception("不能进行删除，你没有该好友");
        }
        return this.removeById(relation);
    }
}

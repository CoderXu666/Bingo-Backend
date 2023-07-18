package com.bingo.store.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoUserRelationMapper;
import com.bingo.pojo.po.BingoUserRelation;
import com.bingo.store.BingoUserRelationStore;
import org.springframework.stereotype.Service;

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
     * 根据user_id查询当前用户的好友关联标识
     */
    @Override
    public List<BingoUserRelation> getRelationsById(Long id) {
        QueryWrapper<BingoUserRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        wrapper.like("relation", id);
        wrapper.orderByDesc("create_time");
        return this.list(wrapper);
    }


    /**
     * 根据 user_id1 和 user_id2 精准查询好友关联
     */
    @Override
    public BingoUserRelation getOneRelationByTwoId(Long userId1, Long userId2) {
        QueryWrapper<BingoUserRelation> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        wrapper.eq("relation", userId1 + ":" + userId2);
        wrapper.or().eq("relation", userId2 + ":" + userId1);
        return this.getOne(wrapper);
    }


    /**
     * 删除当前用户好友id
     */
    @Override
    public Boolean deleteById(Long userId, Long friendId) throws Exception {
//        BingoUserRelation relation = null;
//        List<Long> friendList = this.getListById(userId);
//        for (Long id : friendList) {
//            if (friendId.equals(id)) {
//                QueryWrapper<BingoUserRelation> wrapper1 = new QueryWrapper();
//                wrapper1.eq("user1", friendId);
//                wrapper1.eq("user2", userId);
//                BingoUserRelation userInfo1 = this.getOne(wrapper1);
//                QueryWrapper<BingoUserRelation> wrapper2 = new QueryWrapper();
//                wrapper2.eq("user1", userId);
//                wrapper2.eq("user2", friendId);
//                BingoUserRelation userInfo2 = this.getOne(wrapper2);
//                if (ObjectUtils.isEmpty(userInfo1)) {
//                    relation = userInfo2;
//                } else {
//                    relation = userInfo1;
//                }
//            }
//        }
//        if (ObjectUtils.isEmpty(relation)) {
//            throw new Exception("不能进行删除，你没有该好友");
//        }
//        return this.removeById(relation);
        return null;
    }
}

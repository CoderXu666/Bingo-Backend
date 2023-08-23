package com.bingo.store.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatFriendMapper;
import com.bingo.store.BingoChatFriendStore;
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
public class BingoChatFriendStoreImpl extends ServiceImpl<BingoChatFriendMapper, BingoChatFriend> implements BingoChatFriendStore {

    /**
     * 根据user_id查询当前用户的好友id
     */
    @Override
    public List<Long> getFriendsById(Long id) {
        return baseMapper.getFriendsById(id);
    }


    /**
     * 根据 user_id1 和 user_id2 精准查询好友关联
     */
    @Override
    public BingoChatFriend getRelationByTwoId(Long userId1, Long userId2) {
        QueryWrapper<BingoChatFriend> wrapper = new QueryWrapper<>();
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
//        BingoChatFriend relation = null;
//        List<Long> friendList = this.getListById(userId);
//        for (Long id : friendList) {
//            if (friendId.equals(id)) {
//                QueryWrapper<BingoChatFriend> wrapper1 = new QueryWrapper();
//                wrapper1.eq("user1", friendId);
//                wrapper1.eq("user2", userId);
//                BingoChatFriend userInfo1 = this.getOne(wrapper1);
//                QueryWrapper<BingoChatFriend> wrapper2 = new QueryWrapper();
//                wrapper2.eq("user1", userId);
//                wrapper2.eq("user2", friendId);
//                BingoChatFriend userInfo2 = this.getOne(wrapper2);
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

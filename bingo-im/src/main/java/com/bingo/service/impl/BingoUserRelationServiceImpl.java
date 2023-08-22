package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoUserRelationMapper;
import com.bingo.pojo.po.BingoChatFriend;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.service.BingoChatFriendService;
import com.bingo.store.BingoFriendChatStore;
import com.bingo.store.BingoChatFriendStore;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BingoUserRelationServiceImpl extends ServiceImpl<BingoUserRelationMapper, BingoChatFriend> implements BingoChatFriendService {
    @Autowired
    private UserFeign userFeign;
    @Autowired
    private BingoChatFriendStore relationStore;
    @Autowired
    private BingoFriendChatStore chatStore;


    /**
     * 根据 user_id 查询当前用户的好友（包含聊天记录）
     */
    @Override
    public List<BingoUserVO> getListById(Long userId) {
        return null;
    }


    /**
     * 删除当前用户的好友
     */
    @Override
    public Boolean deleteById(Long userId, Long friendId) throws Exception {
        Boolean isSuccess = relationStore.deleteById(userId, friendId);
        return isSuccess;
    }

    /**
     * 处理关系，根据冒号分割，拼接成一个user_id列表
     */
    private List<Long> splitByColon(List<String> relationList) {
        List<Long> idList = new ArrayList<>();
        // 根据冒号分割成两个 user_id
        for (String relation : relationList) {
            String[] idArr = relation.split(":");
            Long userId1 = Long.parseLong(idArr[0]);
            Long userId2 = Long.parseLong(idArr[1]);
            idList.add(userId1);
            idList.add(userId2);
        }
        return idList;
    }
}

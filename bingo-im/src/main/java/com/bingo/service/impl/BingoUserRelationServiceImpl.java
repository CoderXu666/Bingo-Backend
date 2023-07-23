package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoUserRelationMapper;
import com.bingo.pojo.po.BingoFriendChat;
import com.bingo.pojo.po.BingoUserRelation;
import com.bingo.pojo.resp.FeignResponse;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.service.BingoUserRelationService;
import com.bingo.store.BingoFriendChatStore;
import com.bingo.store.BingoUserRelationStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-05-27
 */
@Service
public class BingoUserRelationServiceImpl extends ServiceImpl<BingoUserRelationMapper, BingoUserRelation> implements BingoUserRelationService {
    @Autowired
    private ChatUserFeign userFeign;
    @Autowired
    private BingoUserRelationStore relationStore;
    @Autowired
    private BingoFriendChatStore chatStore;


    /**
     * 根据 user_id 查询当前用户的好友（包含聊天记录）
     */
    @Override
    public List<BingoUserVO> getListById(Long userId) {
        // 查询好友关联标识(userId : userId)
        List<BingoUserRelation> relations = relationStore.getRelationsById(userId);
        List<String> relationList = relations.stream().map(item -> item.getRelation()).collect(Collectors.toList());

        // 处理关系，根据冒号分割，拼接成一个user_id列表
        List<Long> ids = this.splitByColon(relationList);

        // 去掉当前用户本身的id
        ids = ids.stream().filter(item -> !item.equals(userId)).collect(Collectors.toList());

        // 根据 user_id 集合查询用户信息
        FeignResponse<List<BingoUserVO>> feignResponse = userFeign.getUserInfoByIds(ids);
        List<BingoUserVO> userVOList = feignResponse.getData();

        // 查询好友聊天记录
        for (BingoUserVO userVO : userVOList) {
            BingoUserRelation relation = relationStore.getOneRelationByTwoId(userId, userVO.getId());
            List<BingoFriendChat> chatList = chatStore.getContentsByRelation(relation.getRelation());
            userVO.setChatContentList(chatList);
        }

        return userVOList;
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

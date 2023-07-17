package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.ChatUserFeign;
import com.bingo.mapper.BingoUserRelationMapper;
import com.bingo.pojo.po.BingoUserRelation;
import com.bingo.pojo.resp.FeignResponse;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.service.BingoUserRelationService;
import com.bingo.store.BingoUserRelationStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


    /**
     * 查询当前用户的好友
     */
    @Override
    public List<BingoUserVO> findFriend(Long id) {
        List<Long> ids = relationStore.findFriend(id);
        FeignResponse<List<BingoUserVO>> feignResponse = userFeign.getUserInfoByIds(ids);
        List<BingoUserVO> userVOList = feignResponse.getData();

        // TODO 查询好友聊天记录
        for (BingoUserVO userVO : userVOList) {

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
}

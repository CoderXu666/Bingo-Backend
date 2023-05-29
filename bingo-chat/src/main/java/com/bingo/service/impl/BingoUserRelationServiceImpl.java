package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.ChatUserFeign;
import com.bingo.mapper.BingoUserRelationMapper;
import com.bingo.pojo.po.BingoUserRelation;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.resp.FeignResponse;
import com.bingo.service.BingoUserRelationService;
import com.bingo.store.BingoUserRelationStore;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userVOList;
    }
}
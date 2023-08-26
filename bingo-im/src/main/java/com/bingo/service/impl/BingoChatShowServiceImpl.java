package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoChatShowMapper;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.pojo.vo.user.UserVO;
import com.bingo.service.BingoChatShowService;
import com.bingo.store.BingoChatSendRecordStore;
import com.bingo.store.BingoChatShowStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 聊天窗口列表（展示） 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-24
 */
@Service
public class BingoChatShowServiceImpl extends ServiceImpl<BingoChatShowMapper, BingoChatShow> implements BingoChatShowService {
    @Autowired
    private BingoChatShowStore showStore;
    @Autowired
    private BingoChatSendRecordStore sendRecordStore;
    @Autowired
    private UserFeign userFeign;

    /**
     * 查询聊天列表
     */
    @Override
    public Map<String, List<BingoChatSendRecord>> getChatList(Long userId) {
        // 最终结果集(K:用户信息，V:聊天记录列表)
        Map<String, List<BingoChatSendRecord>> resultMap = new HashMap<>();

        // 查询好友展示列表用户信息
        List<Long> chatShowIds = showStore.getChatShowList(userId);
        List<UserVO> chatShowUserList = userFeign.getUserInfoByIds(chatShowIds).getData();

        // 查询好友群聊信息


        return resultMap;
    }
}

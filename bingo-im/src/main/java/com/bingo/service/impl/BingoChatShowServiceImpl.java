package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoChatShowMapper;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.pojo.vo.user.UserVO;
import com.bingo.service.BingoChatSendRecordService;
import com.bingo.service.BingoChatShowService;
import com.bingo.store.BingoChatShowStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private BingoChatSendRecordService sendRecordService;
    @Autowired
    private UserFeign userFeign;

    /**
     * 查询聊天列表
     */
    @Override
    public Map<String, List<BingoChatSendRecord>> getChatList(Long userId) {
        // 最终结果集(K:用户信息，V:聊天记录列表)
        Map<String, List<BingoChatSendRecord>> resultMap = new HashMap<>();

        // 查询展示列表关联id信息（划分类型：好友id列表、群聊id列表）
        List<BingoChatShow> chatShowList = showStore.getChatShowList(userId);
        Map<Integer, List<BingoChatShow>> showListMap = chatShowList.stream().collect(Collectors.groupingBy(BingoChatShow::getChatType));
        List<BingoChatShow> userChatShowList = showListMap.get(0);
        List<BingoChatShow> groupChatShowList = showListMap.get(1);

        // 查询具体信息：好友、群组
        List<Long> userChatShowIds = userChatShowList.stream().map(BingoChatShow::getId).collect(Collectors.toList());
        List<UserVO> chatUserList = userFeign.getUserByIds(userChatShowIds).getData();
        List<Long> groupChatShowIds = groupChatShowList.stream().map(BingoChatShow::getId).collect(Collectors.toList());

        // 查询好友、群组聊天信息


        return resultMap;
    }
}

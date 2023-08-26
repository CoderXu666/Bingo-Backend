package com.bingo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoChatShowMapper;
import com.bingo.pojo.po.im.BingoChatGroup;
import com.bingo.pojo.po.im.BingoChatGroupSendRecord;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.pojo.vo.im.ChatShowVO;
import com.bingo.pojo.vo.user.UserVO;
import com.bingo.service.BingoChatShowService;
import com.bingo.store.BingoChatGroupSendRecordStore;
import com.bingo.store.BingoChatGroupStore;
import com.bingo.store.BingoChatSendRecordStore;
import com.bingo.store.BingoChatShowStore;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private BingoChatGroupStore chatGroupStore;
    @Autowired
    private BingoChatSendRecordStore sendRecordStore;
    @Autowired
    private BingoChatGroupSendRecordStore groupSendRecordStore;
    @Autowired
    private UserFeign userFeign;

    /**
     * 查询聊天列表
     */
    @Override
    public Map<Object, Object> getChatList(Long userId) {
        // 最终结果集(K:展示对象信息，V:聊天记录列表)，排序展示
        Map<Object, Object> resultMap = new HashMap<>();

        // 查询展示列表关联id信息
        List<BingoChatShow> chatShowList = showStore.getChatShowList(userId);
        if (CollectionUtils.isEmpty(chatShowList)) {
            return resultMap;
        }

        // 根据类型拆分成两个List（好友id列表、群聊id列表）
        Map<Integer, List<BingoChatShow>> showListMap =
                chatShowList.stream().collect(Collectors.groupingBy(BingoChatShow::getChatType));
        List<BingoChatShow> userChatShowList = showListMap.get(0);
        List<BingoChatShow> groupChatShowList = showListMap.get(1);

        // 查询具体目标信息：好友、群组
        // List转Map（避免双重遍历循环，性能太低）
        Map<Long, UserVO> userInfoMap = null;
        Map<Long, BingoChatGroup> groupInfoMap = null;
        if (CollectionUtils.isNotEmpty(userChatShowList)) {
            List<Long> userChatShowIds = userChatShowList.stream().map(BingoChatShow::getGoalId).collect(Collectors.toList());
            List<UserVO> userInfoShowList = userFeign.getUserByIds(userChatShowIds).getData();
            userInfoMap = userInfoShowList.stream().collect(Collectors.toMap(UserVO::getId, item -> item));
        }
        if (CollectionUtils.isNotEmpty(groupChatShowList)) {
            List<Long> groupShowIds = groupChatShowList.stream().map(BingoChatShow::getGoalId).collect(Collectors.toList());
            List<BingoChatGroup> groupInfoShowList = chatGroupStore.getGroupInfoByIds(groupShowIds);
            groupInfoMap = groupInfoShowList.stream().collect(Collectors.toMap(BingoChatGroup::getId, item -> item));
        }

        // 排序：好友、群组列表信息按照最新消息时间
        List<ChatShowVO> chatShowVOList = new ArrayList<>();
        for (BingoChatShow chatShowItem : chatShowList) {
            if (CollectionUtils.isNotEmpty(userInfoMap) && userInfoMap.containsKey(chatShowItem.getId())) {
                UserVO userVO = userInfoMap.get(chatShowItem.getId());
                ChatShowVO chatShowVO = new ChatShowVO();
                BeanUtils.copyProperties(userVO, chatShowVO);
                chatShowVO.setItemName(userVO.getNickName());
                chatShowVO.setType(0);
                chatShowVOList.add(chatShowVO);
            }
            if (CollectionUtils.isNotEmpty(groupInfoMap) && groupInfoMap.containsKey(chatShowItem.getId())) {
                BingoChatGroup groupInfo = groupInfoMap.get(chatShowItem.getId());
                ChatShowVO chatShowVO = new ChatShowVO();
                BeanUtils.copyProperties(groupInfo, chatShowVO);
                chatShowVO.setItemName(groupInfo.getGroupName());
                chatShowVO.setType(1);
                chatShowVOList.add(chatShowVO);
            }
        }

        // 查询好友、群组聊天信息（循环查询吧，有Redis，并且这里条件复杂）
        for (ChatShowVO chatShowVO : chatShowVOList) {
            if (chatShowVO.getType().equals(0)) {
                List<BingoChatSendRecord> sendRecordList = sendRecordStore.getSendRecordList(userId, chatShowVO.getId());
                resultMap.put(chatShowVO, sendRecordList);
            } else {
                List<BingoChatGroupSendRecord> sendRecordList = groupSendRecordStore.getSendRecordList(chatShowVO.getId());
                resultMap.put(chatShowVO, sendRecordList);
            }
        }

        return resultMap;
    }
}

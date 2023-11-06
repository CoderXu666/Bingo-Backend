package com.bingo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.utils.RequestContextUtil;
import com.bingo.exception.BingoException;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoChatShowMapper;
import com.bingo.pojo.po.im.BingoChatSendRecord;
import com.bingo.pojo.po.im.BingoChatShow;
import com.bingo.pojo.vo.user.UserResp;
import com.bingo.service.BingoChatShowService;
import com.bingo.store.BingoChatSendRecordStore;
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
    private BingoChatSendRecordStore sendRecordStore;
    @Autowired
    private UserFeign userFeign;

    /**
     * 查询聊天会话列表
     */
    @Override
    public Map<String, Object> getChatList() throws Exception {
        // 请求全局域获取 uid
        Map<String, Object> resultMap = new HashMap<>();
        Long uid = (Long) RequestContextUtil.get().get("uid");

        // 好友会话列表
        List<BingoChatShow> chatShowList = showStore.getChatShowList(uid);
        if (CollectionUtils.isEmpty(chatShowList)) {
            return resultMap;
        }

        // 查询好友会话列表-详细用户信息
        List<Long> userChatShowIds = chatShowList.stream().map(BingoChatShow::getGoalId).collect(Collectors.toList());
        List<UserResp> userChatShowList = userFeign.getUserByIds(userChatShowIds).getData();

        // 查询好友聊天信息（循环查询吧，Redis做好缓存，并且这里是首次连接登录，不需要加载特别快）
        Map<Long, Object> recordMap = new HashMap<>();
        for (UserResp userResp : userChatShowList) {
            List<BingoChatSendRecord> sendRecordList = sendRecordStore.getSendRecordList(uid, userResp.getUid());
            List<BingoChatSendRecord> finalRecords = sendRecordList.stream().limit(20).collect(Collectors.toList());
            recordMap.put(userResp.getUid(), finalRecords);
        }
        resultMap.put("chatShow", userChatShowList);
        resultMap.put("chatRecord", recordMap);
        return resultMap;
    }

    /**
     * 清空未读数量
     */
    @Override
    public Boolean clearUnread(Long goalId) throws Exception {
        if (ObjectUtils.isEmpty(goalId)) {
            throw new BingoException(null);
        }
        BingoChatShow record = showStore.getOneRecord((Long) RequestContextUtil.get().get("uid"), goalId);
        if (ObjectUtils.isEmpty(record)) {
            throw new BingoException(null);
        }
        record.setUnreadCount(0);
        return showStore.updateRecordById(record);
    }
}

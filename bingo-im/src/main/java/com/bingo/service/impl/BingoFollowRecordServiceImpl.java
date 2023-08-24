package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoFollowRecordMapper;
import com.bingo.pojo.BingoChatSendRecord;
import com.bingo.pojo.po.BingoFollowRecord;
import com.bingo.service.BingoFollowRecordService;
import com.bingo.store.BingoChatSendRecordStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户关注表 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
@Service
public class BingoFollowRecordServiceImpl extends ServiceImpl<BingoFollowRecordMapper, BingoFollowRecord> implements BingoFollowRecordService {
    @Autowired
    private BingoChatSendRecordStore sendRecordStore;
    @Autowired
    private UserFeign userFeign;


    /**
     * 查询聊天好友（包含聊天记录）
     * -----------------------------------------
     * CompletableFuture异步处理
     * 使用supplyAsync()，因为可以拿到返回值
     */
    @Override
    public Map<String, Object> getChatList(Long userId) {
        // 最终结果集
        Map<String, Object> resultMap = new HashMap<>();

        // 线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                4,
                Integer.MAX_VALUE,
                60L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );

        // 通过user_id查看聊天消息
        List<BingoChatSendRecord> chatSendRecords = sendRecordStore.getSendRecordList(userId);



//        // 互关好友 + 聊天记录
//        List<BingoFollowRecord> friendList = followRecordStore.getFriendList(userId);
//        if (CollectionUtils.isNotEmpty(friendList)) {
//            List<Long> ids = friendList.stream().map(item -> item.getId()).collect(Collectors.toList());
//            List<BingoUserVO> userInfoList = userFeign.getUserInfoByIds(ids).getData();
//            Map<Long, List<BingoChatSendRecord>> chatRecordMap = sendRecordService.getChatRecordByUserId(userId, ids);
//        }
//
//        // 关注者 + 聊天记录
//        List<BingoFollowRecord> followList = followRecordStore.getFollowList(userId);
//        if (CollectionUtils.isNotEmpty(followList)) {
//            List<Long> ids = followList.stream().map(item -> item.getId()).collect(Collectors.toList());
//            List<BingoUserVO> userInfoList = userFeign.getUserInfoByIds(ids).getData();
//        }
//
//        // 粉丝 + 聊天记录
//        List<BingoFollowRecord> fanList = followRecordStore.getFanList(userId);
//        if (CollectionUtils.isNotEmpty(fanList)) {
//            List<Long> ids = fanList.stream().map(item -> item.getId()).collect(Collectors.toList());
//            List<BingoUserVO> userInfoList = userFeign.getUserInfoByIds(ids).getData();
//        }

        return null;
    }
}

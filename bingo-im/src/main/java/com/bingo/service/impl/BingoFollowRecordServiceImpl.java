package com.bingo.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.feign.UserFeign;
import com.bingo.mapper.BingoFollowRecordMapper;
import com.bingo.pojo.po.BingoFollowRecord;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.service.BingoFollowRecordService;
import com.bingo.store.BingoFollowRecordStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    private BingoFollowRecordStore followRecordStore;
    @Autowired
    private UserFeign userFeign;


    /**
     * 查询聊天好友（包含聊天记录）
     * --------------------------------------
     * TODO 多线程异步处理吧，要不太慢了！
     */
    @Override
    public Map<String, Object> getChatList(Long userId) {
        // 最终结果集
        Map<String, Object> resultMap = new HashMap<>();

        // 互关好友 + 聊天记录
        List<BingoFollowRecord> friendList = followRecordStore.getFriendList(userId);
        if (CollectionUtils.isNotEmpty(friendList)) {
            List<Long> ids = friendList.stream().map(item -> item.getId()).collect(Collectors.toList());
            List<BingoUserVO> userInfoList = userFeign.getUserInfoByIds(ids).getData();
        }

        // 关注者 + 聊天记录
        List<BingoFollowRecord> followList = followRecordStore.getFollowList(userId);
        if (CollectionUtils.isNotEmpty(followList)) {
            List<Long> ids = followList.stream().map(item -> item.getId()).collect(Collectors.toList());
            List<BingoUserVO> userInfoList = userFeign.getUserInfoByIds(ids).getData();
        }

        // 粉丝 + 聊天记录
        List<BingoFollowRecord> fanList = followRecordStore.getFanList(userId);
        if (CollectionUtils.isNotEmpty(fanList)) {
            List<Long> ids = fanList.stream().map(item -> item.getId()).collect(Collectors.toList());
            List<BingoUserVO> userInfoList = userFeign.getUserInfoByIds(ids).getData();
        }

        return null;
    }
}

package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoChatSendRecordMapper;
import com.bingo.pojo.BingoChatSendRecord;
import com.bingo.service.BingoChatSendRecordService;
import com.bingo.store.BingoChatSendRecordStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 好友聊天记录表 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-23
 */
@Service
public class BingoChatSendRecordServiceImpl extends ServiceImpl<BingoChatSendRecordMapper, BingoChatSendRecord> implements BingoChatSendRecordService {
    @Autowired
    private BingoChatSendRecordStore sendRecordStore;

    /**
     * 查询好友聊天记录
     *
     * @param userId 用户id
     * @param ids    好友列表id
     */
    @Override
    public Map<Long, List<BingoChatSendRecord>> getChatRecordByUserId(Long userId, List<Long> ids) {
//        Map<Long, List<BingoChatSendRecord>> resultMap = new HashMap<>();
        return null;
    }
}

package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoNoticeLogMapper;
import com.bingo.pojo.po.BingoNoticeLog;
import com.bingo.service.BingoNoticeLogService;
import com.bingo.store.BingoNoticeLogStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-04
 */
@Service
public class BingoNoticeLogServiceImpl extends ServiceImpl<BingoNoticeLogMapper, BingoNoticeLog> implements BingoNoticeLogService {

    @Autowired
    BingoNoticeLogStore noticeLogStore;

    /**
     * 根据Id,通知类型查询用用户的通知
     */
    @Override
    public List<BingoNoticeLog> find(String id, Integer type) {
        List<BingoNoticeLog> bingoNoticeLog = noticeLogStore.find(id, type);
        return bingoNoticeLog;
    }

    /**
     * 保存用户通知
     */
    @Override
    public Boolean saveNotice(BingoNoticeLog noticeLog) {
        return noticeLogStore.saveNotice(noticeLog);
    }
}

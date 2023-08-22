package com.bingo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoSystemNoticeMapper;
import com.bingo.pojo.po.BingoSystemNotice;
import com.bingo.service.BingoSystemNoticeService;
import com.bingo.store.BingoSystemNoticeStore;
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
public class BingoSystemNoticeServiceImpl extends ServiceImpl<BingoSystemNoticeMapper, BingoSystemNotice> implements BingoSystemNoticeService {

    @Autowired
    private BingoSystemNoticeStore noticeLogStore;

    /**
     * 根据Id,通知类型查询用用户的通知
     */
    @Override
    public List<BingoSystemNotice> findByIdAndType(Long id, Integer type) {
        List<BingoSystemNotice> BingoSystemNotice = noticeLogStore.findByIdAndType(id, type);
        return BingoSystemNotice;
    }

    /**
     * 保存用户通知
     */
    @Override
    public Boolean saveNotice(BingoSystemNotice noticeLog) {
        return noticeLogStore.saveNotice(noticeLog);
    }
}

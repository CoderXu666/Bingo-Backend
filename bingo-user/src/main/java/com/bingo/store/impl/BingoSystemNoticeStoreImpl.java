package com.bingo.store.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoSystemNoticeMapper;
import com.bingo.pojo.po.BingoSystemNotice;
import com.bingo.store.BingoSystemNoticeStore;
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
public class BingoSystemNoticeStoreImpl extends ServiceImpl<BingoSystemNoticeMapper, BingoSystemNotice> implements BingoSystemNoticeStore {

    /**
     * 根据Id,通知类型查询用用户的通知
     */
    @Override
    public List<BingoSystemNotice> findByIdAndType(Long id, Integer type) {
        QueryWrapper<BingoSystemNotice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        queryWrapper.eq("notice_type", type);
        List<BingoSystemNotice> list = this.list(queryWrapper);
        return list;
    }

    /**
     * 保存用户通知
     */
    @Override
    public Boolean saveNotice(BingoSystemNotice noticeLog) {
        return this.save(noticeLog);
    }
}

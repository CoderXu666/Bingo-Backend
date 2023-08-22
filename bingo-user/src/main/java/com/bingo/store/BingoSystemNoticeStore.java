package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoSystemNotice;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-04
 */
public interface BingoSystemNoticeStore extends IService<BingoSystemNotice> {
    List<BingoSystemNotice> findByIdAndType(Long id, Integer type);
    Boolean saveNotice(BingoSystemNotice noticeLog);
}

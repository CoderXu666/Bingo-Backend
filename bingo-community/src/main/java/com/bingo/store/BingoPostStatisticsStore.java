package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoPostStatistics;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-06-18
 */
public interface BingoPostStatisticsStore extends IService<BingoPostStatistics> {
    List<BingoPostStatistics> findPost(List<Long> ids);
}

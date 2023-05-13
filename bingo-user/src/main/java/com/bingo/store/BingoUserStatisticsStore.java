package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoUserStatistics;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
public interface BingoUserStatisticsStore extends IService<BingoUserStatistics> {
    /**
     * 根据userId查询用户信息
     */
    BingoUserStatistics findUserSta(Long userId);
}

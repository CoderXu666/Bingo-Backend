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
     * 根据userName查询用户信息
     *
     * @param userName
     * @return
     */
    BingoUserStatistics findUserSta(String userName);
}

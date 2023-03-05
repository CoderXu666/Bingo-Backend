package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoUserMapper;
import com.bingo.pojo.po.BingoUser;
import com.bingo.pojo.po.BingoUserStatistics;
import com.bingo.pojo.vo.BingoUserVO;
import com.bingo.service.BingoUserService;
import com.bingo.store.BingoUserStatisticsStore;
import com.bingo.store.BingoUserStore;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
@Service
public class BingoUserServiceImpl extends ServiceImpl<BingoUserMapper, BingoUser> implements BingoUserService {
    @Autowired
    private BingoUserStatisticsStore bingoUserStatisticsStore;
    @Autowired
    private BingoUserStore bingoUserStore;

    /**
     * 根据userName查询用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public BingoUserVO findUserInfo(String userName) {
        BingoUserVO bingoUserVO = new BingoUserVO();
        BingoUser bingoUser = bingoUserStore.findUserInfo(userName);
        BingoUserStatistics bingoUserStatistics = bingoUserStatisticsStore.findUserSta(userName);
        if (ObjectUtils.isNotEmpty(bingoUser)) {
            BeanUtils.copyProperties(bingoUser, bingoUserVO);
        }
        if (ObjectUtils.isNotEmpty(bingoUserStatistics)) {
            BeanUtils.copyProperties(bingoUserStatistics, bingoUserVO);
        }
        return bingoUserVO;
    }
}

package com.bingo.store.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoFollowLogMapper;
import com.bingo.pojo.po.community.BingoFollowLog;
import com.bingo.store.BingoFollowLogStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 关注操作日志 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-08-25
 */
@Service
public class BingoFollowLogStoreImpl extends ServiceImpl<BingoFollowLogMapper, BingoFollowLog> implements BingoFollowLogStore {

}

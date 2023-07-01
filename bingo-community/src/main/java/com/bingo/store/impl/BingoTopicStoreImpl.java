package com.bingo.store.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoTopicMapper;
import com.bingo.pojo.po.BingoTopic;
import com.bingo.service.BingoTopicService;
import com.bingo.store.BingoTopicStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-07-01
 */
@Service
public class BingoTopicStoreImpl extends ServiceImpl<BingoTopicMapper, BingoTopic> implements BingoTopicStore {

}

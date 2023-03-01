package com.bingo.store.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoAdviceMapper;
import com.bingo.pojo.po.BingoAdvice;
import com.bingo.store.BingoAdviceStore;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户建议表 服务实现类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
@Service
public class BingoAdviceStoreImpl extends ServiceImpl<BingoAdviceMapper, BingoAdvice> implements BingoAdviceStore {

    /**
     * 保存建议和评价
     */
    @Override
    public Boolean saveAdvice(BingoAdvice bingoAdvice) {
        boolean isSuccess = this.save(bingoAdvice);
        return isSuccess;
    }
}

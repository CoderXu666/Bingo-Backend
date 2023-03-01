package com.bingo.store;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.po.BingoAdvice;

/**
 * <p>
 * 用户建议表 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
public interface BingoAdviceStore extends IService<BingoAdvice> {

    /**
     * 保存建议和评价
     */
    Boolean saveAdvice(BingoAdvice bingoAdvice);
}

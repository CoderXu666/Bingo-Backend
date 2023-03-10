package com.bingo.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.bingo.pojo.dto.BingoAdviceDTO;
import com.bingo.pojo.po.BingoAdvice;

/**
 * <p>
 * 用户建议表 服务类
 * </p>
 *
 * @author 徐志斌
 * @since 2023-03-01
 */
public interface BingoAdviceService extends IService<BingoAdvice> {

    /**
     * 保存建议和评价
     *
     * @param bingoAdviceDTO
     * @return
     */
    Boolean saveAdvice(BingoAdviceDTO bingoAdviceDTO);
}

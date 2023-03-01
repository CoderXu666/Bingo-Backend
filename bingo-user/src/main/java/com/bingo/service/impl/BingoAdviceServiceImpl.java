package com.bingo.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bingo.mapper.BingoAdviceMapper;
import com.bingo.pojo.dto.BingoAdviceDTO;
import com.bingo.pojo.po.BingoAdvice;
import com.bingo.service.BingoAdviceService;
import com.bingo.store.BingoAdviceStore;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class BingoAdviceServiceImpl extends ServiceImpl<BingoAdviceMapper, BingoAdvice> implements BingoAdviceService {

    @Autowired
    private BingoAdviceStore bingoAdviceStore;

    /**
     * 保存建议和评价
     *
     * @param bingoAdviceDTO
     * @return
     */
    @Override
    public Boolean saveAdvice(BingoAdviceDTO bingoAdviceDTO) {
        BingoAdvice bingoAdvice = new BingoAdvice();
        if (StringUtils.isNotEmpty(bingoAdviceDTO.getUserName())) {
            BeanUtils.copyProperties(bingoAdviceDTO, bingoAdvice);
        } else {
            bingoAdviceDTO.setUserName("未知用户");
            BeanUtils.copyProperties(bingoAdviceDTO, bingoAdvice);
        }
        Boolean isSuccess = bingoAdviceStore.saveAdvice(bingoAdvice);
        return isSuccess;
    }
}

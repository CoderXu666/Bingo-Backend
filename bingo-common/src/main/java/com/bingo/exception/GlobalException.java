package com.bingo.exception;

import com.bingo.enums.RespCodeEnum;
import com.bingo.pojo.resp.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author 徐志斌
 * @Date: 2023/6/30 21:22
 * @Version 1.0
 * @Description: GlobalExceptionHandler
 */
@Slf4j
@RestControllerAdvice
public class GlobalException {
    /**
     * 所有异常
     */
    @ExceptionHandler(Exception.class)
    public R bindException(Exception e) {
        log.error("--------------------Exception异常：{}--------------------", e.getMessage());
        return R.out(RespCodeEnum.FAIL, e.getMessage());
    }
}

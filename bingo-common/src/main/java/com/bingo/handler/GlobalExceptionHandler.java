package com.bingo.handler;

import com.bingo.enums.ResponseEnum;
import com.bingo.exception.BingoException;
import com.bingo.response.R;
import io.jsonwebtoken.ExpiredJwtException;
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
public class GlobalExceptionHandler {
    /**
     * JWT Token过期解析异常
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public R expiredJwtException(ExpiredJwtException e) {
        log.error("========================ExpiredJwtException：{}========================", e);
        return R.out(ResponseEnum.TOKEN_EXPIRED, e);
    }

    /**
     * 自定义异常
     */
    @ExceptionHandler(BingoException.class)
    public R bingoException(BingoException e) {
        log.error("========================BingoException：{}========================", e);
        return R.out(ResponseEnum.TOKEN_EXPIRED, e);
    }

    /**
     * 所有异常
     */
    @ExceptionHandler(Exception.class)
    public R bindException(Exception e) {
        log.error("========================Exception：{}========================", e);
        return R.out(ResponseEnum.FAIL, e);
    }
}

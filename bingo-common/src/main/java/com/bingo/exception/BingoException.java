package com.bingo.exception;

import com.bingo.enums.ResponseEnum;
import lombok.AllArgsConstructor;

/**
 * @Author: 徐志斌
 * @CreateTime: 2023-09-11  17:56
 * @Description: 自定义异常
 * @Version: 1.0
 */
@AllArgsConstructor
public class BingoException extends RuntimeException {
    private ResponseEnum responseEnum;
}

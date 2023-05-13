package com.bingo.resp;

import lombok.Data;

/**
 * @Author 徐志斌
 * @Date: 2023/5/13 12:05
 * @Version 1.0
 * @Description: 远程调用返回值封装类
 */
@Data
public class FeignResult<T> {
    private Integer code;
    private String msg;
    private T data;
}

package com.bingo.pojo.response;

import com.bingo.enums.RespCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author 徐志斌
 * @Date: 2023/5/8 21:59
 * @Version 1.0
 * @Description: 响应封装类：前后端统一消息格式
 */
@Getter
@Setter
public class R<T> extends BaseResponse {
    private T data;

    private R(RespCodeEnum code) {
        super(code);
    }

    private R(RespCodeEnum code, T data) {
        super(code);
        this.data = data;
    }

    public static BaseResponse out(RespCodeEnum code) {
        return new BaseResponse(code);
    }

    public static <T> R<T> out(RespCodeEnum code, T data) {
        return new R<>(code, data);
    }
}

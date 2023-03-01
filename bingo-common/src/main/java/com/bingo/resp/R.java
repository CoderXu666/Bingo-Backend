package com.bingo.resp;

import com.google.common.collect.Maps;
import lombok.ToString;

import java.util.Map;

@ToString
public class R<T> extends AbstractMappingJacksonValue<T> implements IResult<T> {

    private Map<String, Object> value;

    public R(T data, long code, String msg) {
        super(data);
        this.value = Maps.newHashMap();
        this.value.put(RESP_CODE, code);
        this.value.put(RESP_MSG, msg);
        this.value.put(DATA, data);
        this.setValue(this.value);
    }

    public static <T> R<T> succeed(T data, String msg) {
        return new R<>(data, 200, msg);
    }

    public static <T> R<T> failed(T data, String msg) {
        return new R<>(data, 500, msg);
    }
}

package com.bingo.resp;

import com.google.common.collect.Maps;
import lombok.ToString;

import java.util.Map;

@ToString
public class Bingo<T> extends AbstractMappingJacksonValue<T> implements IResult<T> {

    private Map<String, Object> value;

    public Bingo(T data, long code, String msg) {
        super(data);
        this.value = Maps.newHashMap();
        this.value.put(RESP_CODE, code);
        this.value.put(RESP_MSG, msg);
        this.value.put(DATA, data);
        this.setValue(this.value);
    }


    public static <T> Bingo<T> succeed(T data) {
        return succeed(data, "");
    }

    public static <T> Bingo<T> succeed(T data, String msg) {
        return new Bingo<>(data, 200, msg);
    }

    public static <T> Bingo<T> failed(String msg) {
        return new Bingo<>(null, 500, msg);
    }
}

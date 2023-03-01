package com.bingo.resp;

import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

public abstract class AbstractMappingJacksonValue<T> extends MappingJacksonValue implements IResult<T> {

    SimpleFilterProvider filter;


    public AbstractMappingJacksonValue(Object value) {
        super(value);
        this.filter = new SimpleFilterProvider();
        this.filter = new SimpleFilterProvider();
        this.filter.setFailOnUnknownId(false);
        this.setFilters(this.filter);
    }
}

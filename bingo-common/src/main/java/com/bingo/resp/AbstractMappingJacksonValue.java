package com.bingo.resp;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;

import java.util.Set;

/**
 *
 * @author zhangbenfu
 * @date 23/07/2020
 */
public abstract class AbstractMappingJacksonValue<T> extends MappingJacksonValue implements IResult<T> {

    SimpleFilterProvider filter;


    public AbstractMappingJacksonValue(Object value) {
        super(value);
        this.filter = new SimpleFilterProvider();
        this.filter = new SimpleFilterProvider();
        this.filter.setFailOnUnknownId(false);
        this.setFilters(this.filter);
    }

    /**
     * 过滤要输出的属性
     *
     * @param id            过滤器ID
     * @param propertyArray 属性名，在这里出现的属性将会输出。
     * @return
     */
    @Override
    public IResult<T> filterOutAllExcept(String id, String... propertyArray) {
        this.filter.addFilter(id, SimpleBeanPropertyFilter.filterOutAllExcept(propertyArray));
        return this;
    }

    /**
     * 过滤要输出的属性
     *
     * @param id         过滤器ID
     * @param properties 属性名，在这里出现的属性将会输出。
     * @return
     */
    @Override
    public IResult<T> filterOutAllExcept(String id, Set<String> properties) {
        this.filter.addFilter(id, SimpleBeanPropertyFilter.filterOutAllExcept(properties));
        return this;
    }

    /**
     * 过滤输出的属性
     *
     * @param id            过滤器ID
     * @param propertyArray 属性名，在这里出现的属性将不会输出。
     * @return
     */
    @Override
    public IResult<T> serializeAllExcept(String id, String... propertyArray) {
        this.filter.addFilter(id, SimpleBeanPropertyFilter.serializeAllExcept(propertyArray));
        return this;
    }

    /**
     * 过滤输出的属性
     *
     * @param id         过滤器ID
     * @param properties 属性名，在这里出现的属性将不会输出。
     * @return
     */
    @Override
    public IResult<T> serializeAllExcept(String id, Set<String> properties) {
        this.filter.addFilter(id, SimpleBeanPropertyFilter.serializeAllExcept(properties));
        return this;
    }
}

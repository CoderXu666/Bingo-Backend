package com.bingo.resp;

import java.util.Set;


public interface IResult<B> {
    String RESP_CODE = "resp_code";
    String DATA = "data";
    String RESP_MSG = "resp_msg";

    IResult<B> filterOutAllExcept(String id, String... propertyArray);
    IResult<B> filterOutAllExcept(String id, Set<String> properties);
    IResult<B> serializeAllExcept(String id, String... propertyArray);
    IResult<B> serializeAllExcept(String id, Set<String> properties);
}


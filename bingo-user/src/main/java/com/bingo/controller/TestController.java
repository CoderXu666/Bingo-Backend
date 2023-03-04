package com.bingo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.benmanes.caffeine.cache.Cache;
import org.elasticsearch.client.IndicesClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/3/1
 * @description:
 * @Version: 1.
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private Cache<String, Object> cache;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @RequestMapping("/caffeine")
    public String test() {
        IndicesClient indices = restHighLevelClient.indices();
        return JSONObject.toJSONString(indices);
    }
}
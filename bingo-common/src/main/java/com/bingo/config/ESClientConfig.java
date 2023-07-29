package com.bingo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 徐志斌
 * @Date: 2023/5/20 21:21
 * @Version 1.0
 * @Description: ElasticSearch客户端
 */
@Configuration
public class ESClientConfig {
    @Value("")
    private String ipAddress;
    @Value("")
    private Integer port;

    /**
     * RestHighLevel Client
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        HttpHost host = new HttpHost("101.42.13.186", 9200, "http");
        return new RestHighLevelClient(RestClient.builder(host));
    }
}

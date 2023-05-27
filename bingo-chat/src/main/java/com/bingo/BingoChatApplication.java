package com.bingo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author 徐志斌
 * @Date: 2023/5/27 17:23
 * @Version 1.0
 * @Description: BingoChatApplication
 */
@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
@ComponentScan("com.bingo.*")
@MapperScan("com.bingo.mapper")
@SpringBootApplication
public class BingoChatApplication {
    public static void main(String[] args) {
        SpringApplication.run(BingoChatApplication.class);
    }
}

package com.bingo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/3/1
 * @description:
 * @Version: 1.0
 */
@EnableDiscoveryClient
@ComponentScan("com.bingo.*")
@MapperScan("com.bingo.mapper")
@SpringBootApplication
public class BingoUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(BingoUserApplication.class);
    }
}
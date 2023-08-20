package com.bingo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/8/20
 * @Version: 1.0
 */
@EnableFeignClients
@ComponentScan("com.bingo.*")
@MapperScan("com.bingo.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class BingoRechargeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BingoRechargeApplication.class, args);
    }
}

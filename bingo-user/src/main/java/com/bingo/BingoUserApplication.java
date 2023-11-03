package com.bingo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

import java.util.TimeZone;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/3/1
 * @Version: 1.0
 */
@Slf4j
@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.bingo.mapper")
@SpringBootApplication
public class BingoUserApplication implements CommandLineRunner {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(BingoUserApplication.class);
    }

    @Override
    public void run(String... args) {
        String applicationName = environment.getProperty("spring.application.name");
        String serverPort = environment.getProperty("server.port");
        log.info("----------------------------开发者平台服务名称：{}---------------", applicationName);
        log.info("----------------------------开发者平台服务端口：{}-----------------------", serverPort);
        log.info("----------------------------开发者平台服务【海外】启动成功......-----------------------");
    }
}
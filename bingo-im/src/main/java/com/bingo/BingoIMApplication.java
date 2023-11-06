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
 * @Author 徐志斌
 * @Date: 2023/5/27 17:23
 * @Version 1.0
 */
@Slf4j
@EnableCaching
@EnableFeignClients
@EnableDiscoveryClient
@MapperScan("com.bingo.mapper")
@SpringBootApplication
public class BingoIMApplication implements CommandLineRunner {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(BingoIMApplication.class);
    }

    @Override
    public void run(String... args) {
        String applicationName = environment.getProperty("spring.application.name");
        String serverPort = environment.getProperty("server.port");
        log.info("----------------------------【Bingo】通讯服务名称：{}---------------", applicationName);
        log.info("----------------------------【Bingo】通讯服务端口：{}-----------------------", serverPort);
        log.info("----------------------------【Bingo】通讯服务启动成功......-----------------------");
    }
}

package com.bingo;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

import java.util.TimeZone;

/**
 * @Author: 徐志斌
 * @CreateDate: 2023/3/4
 * @Version: 1.0
 */
@Slf4j
@EnableFeignClients
@ComponentScan("com.bingo.*")
@MapperScan("com.bingo.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class BingoPartyApplication implements CommandLineRunner {
    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(BingoPartyApplication.class);
    }

    @Override
    public void run(String... args) {
        String applicationName = environment.getProperty("spring.application.name");
        String serverPort = environment.getProperty("server.port");
        log.info("----------------------------【Bingo】派对服务启动：{}---------------", applicationName);
        log.info("----------------------------【Bingo】派对服务端口：{}-----------------------", serverPort);
        log.info("----------------------------【Bingo】派对服务启动成功......-----------------------");
    }
}
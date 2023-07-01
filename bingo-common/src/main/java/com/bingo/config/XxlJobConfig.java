package com.bingo.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 徐志斌
 * @Date: 2023/5/20 22:33
 * @Version 1.0
 * @Description: XXL-JOB Config
 */
@Slf4j
@Configuration
public class XxlJobConfig {
    @Value("${xxl.job.admin.addresses}")
    private String adminAddresses;

    @Value("${xxl.job.accessToken}")
    private String accessToken;

    @Value("${xxl.job.executor.appname}")
    private String appname;

    @Value("${xxl.job.executor.address}")
    private String address;

    @Value("${xxl.job.executor.ip}")
    private String ip;

    @Value("${xxl.job.executor.port}")
    private int port;

    @Value("${xxl.job.executor.logpath}")
    private String logPath;

    @Value("${xxl.job.executor.logretentiondays}")
    private int logRetentionDays;

    /**
     * xxl:
     *   job:
     *     admin:
     *       addresses: http://101.42.13.186:8800/xxl-job-admin/
     *     accessToken: Lpoms_xxljob_default_token
     *     executor:
     *       appname: bingo
     *       address: http://101.42.13.186:9999
     *       ip: 101.42.13.186
     *       port: 9999
     *       logpath: logs/xxl-job/jobhandler
     *       logretentiondays: 7
     *
     *
     * xxl:
     *   job:
     *     accessToken: xdclass.net
     *     admin:
     *       addresses: http://54.223.25.1:9093/xxl-job-admin/
     *     executor:
     *       address: http://172.29.0.15:9999
     *       appname: xxl-job-executor-sample
     *       ip: ''
     *       logpath: /data/applogs/xxl-job/jobhandler
     *       logretentiondays: 30
     *       port: 9999
     */
    @Bean
    public XxlJobSpringExecutor xxlJobExecutor() {
        log.info(">>>>>>>>>>> xxl-job config init. >>>>>>>>>>>");
        XxlJobSpringExecutor xxlJobExecutor = new XxlJobSpringExecutor();
        xxlJobExecutor.setAdminAddresses("http://101.42.13.186:8800/xxl-job-admin/");
        xxlJobExecutor.setAppname("bingo-executor");
        xxlJobExecutor.setAddress("http://101.42.13.186");
        xxlJobExecutor.setPort(10001);
        xxlJobExecutor.setAccessToken("Lpoms_xxljob_default_token");
        xxlJobExecutor.setLogPath("logs/xxl-job/jobhandler");
        xxlJobExecutor.setLogRetentionDays(7);
        return xxlJobExecutor;
    }
}

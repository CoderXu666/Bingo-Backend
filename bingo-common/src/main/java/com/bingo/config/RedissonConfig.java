//package com.bingo.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.Redisson;
//import org.redisson.api.RedissonClient;
//import org.redisson.config.ClusterServersConfig;
//import org.redisson.config.Config;
//import org.redisson.config.SentinelServersConfig;
//import org.redisson.config.SingleServerConfig;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
///**
// * @Author 徐志斌
// * @Date: 2023/5/11 22:11
// * @Version 1.0
// * @Description: RedissonConfig
// */
//@Slf4j
//@Configuration
//public class RedissonConfig {
//    private static final String SCHEMA_PREFIX = "redis://";
//
//    @Bean
//    public RedissonClient redissonClient(RedisProperties redisProperties) {
//        Config config = new Config();
//        RedisProperties.Sentinel sentinel = redisProperties.getSentinel();
//        RedisProperties.Cluster redisPropertiesCluster = redisProperties.getCluster();
//        /**
//         * 集群模式
//         */
//        if (redisPropertiesCluster != null) {
//            ClusterServersConfig clusterServersConfig = config.useClusterServers();
//            for (String cluster : redisPropertiesCluster.getNodes()) {
//                clusterServersConfig.addNodeAddress(SCHEMA_PREFIX + cluster);
//            }
//            if (StringUtils.hasText(redisProperties.getPassword())) {
//                clusterServersConfig.setPassword(redisProperties.getPassword());
//            }
//            clusterServersConfig.setTimeout((int) redisProperties.getTimeout().toMillis());
//            clusterServersConfig.setPingConnectionInterval(30000);
//        }
//
//        /**
//         * 单机Redis
//         */
//        else if (StringUtils.hasText(redisProperties.getHost())) {
//            //单点redis
//            SingleServerConfig singleServerConfig = config.useSingleServer().
//                    setAddress(SCHEMA_PREFIX + redisProperties.getHost() + ":" + redisProperties.getPort());
//            if (StringUtils.hasText(redisProperties.getPassword())) {
//                singleServerConfig.setPassword(redisProperties.getPassword());
//            }
//            singleServerConfig.setTimeout((int) redisProperties.getTimeout().toMillis());
//            singleServerConfig.setPingConnectionInterval(30000);
//            singleServerConfig.setDatabase(redisProperties.getDatabase());
//        }
//
//        /**
//         * 哨兵模式
//         */
//        else if (sentinel != null) {
//            SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
//            sentinelServersConfig.setMasterName(sentinel.getMaster());
//            for (String node : sentinel.getNodes()) {
//                sentinelServersConfig.addSentinelAddress(SCHEMA_PREFIX + node);
//            }
//            if (StringUtils.hasText(redisProperties.getPassword())) {
//                sentinelServersConfig.setPassword(redisProperties.getPassword());
//            }
//            sentinelServersConfig.setTimeout((int) redisProperties.getTimeout().toMillis());
//            sentinelServersConfig.setPingConnectionInterval(30000);
//            sentinelServersConfig.setDatabase(redisProperties.getDatabase());
//        }
//        return Redisson.create(config);
//    }
//}

package com.bingo.kafka;

import com.bingo.constant.MQTopicConstant;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Author 徐志斌
 * @Date: 2023/5/13 21:45
 * @Version 1.0
 * @Description: Kafka工具类
 */
public class KafkaAdminClient {
    private static String url = "101.42.13.186:9092";

    public static void main(String[] args) throws Exception {
//        createTopic(MQTopicConstant.COMMUNITY_POST_TOPIC);
//        createTopic(MQTopicConstant.POST_LIKE_TOPIC);
        getTopic();
//        deleteTopic("");
    }

    /**
     * 创建Topic
     */
    public static void createTopic(String topicName) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, url);
        try (AdminClient adminClient = AdminClient.create(props)) {
            NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
            adminClient.createTopics(Collections.singleton(newTopic));
        }
    }

    /**
     * 查询Topic
     */
    public static void getTopic() throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", url);
        AdminClient adminClient = AdminClient.create(props);
        ListTopicsResult topicsResult = adminClient.listTopics();
        System.out.println("============================================" + topicsResult.names().get());
    }

    /**
     * 删除Topic
     */
    public static void deleteTopic(String topicName) {
        Properties props = new Properties();
        props.put("bootstrap.servers", url);
        AdminClient adminClient = AdminClient.create(props);
        adminClient.deleteTopics(Collections.singleton(topicName));
    }
}
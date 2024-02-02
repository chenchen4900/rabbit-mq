package com.chen.rabbitmq.topic;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 发送者
 * @Author: ChenChen
 * @Date: 2024-02-01 17:28
 * @Version: 1.0
 **/
@Component
public class TopicSender {

    @Autowired
    AmqpTemplate amqpTemplate;

    public void send1(Object userInfo){
        amqpTemplate.convertAndSend("topicExchange", "topic.user", userInfo);
    }

    public void send2(Object productInfo){
        amqpTemplate.convertAndSend("topicExchange", "topic.product", productInfo);
    }
}

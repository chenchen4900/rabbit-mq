package com.chen.rabbitmq.head;

import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 生产者
 * @Author: ChenChen
 * @Date: 2024-02-04 11:44
 * @Version: 1.0
 **/
@Component
public class HeadMQSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object message){
        rabbitTemplate.convertAndSend("headExchange", "", message, config -> {
            MessageProperties properties = config.getMessageProperties();
            //设置消息是否持久化 PERSISTENT-持久化 NON_PERSISTENT-不持久化
            properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
            properties.setHeader("attr1", "A");
            return config;
        });
    }
}

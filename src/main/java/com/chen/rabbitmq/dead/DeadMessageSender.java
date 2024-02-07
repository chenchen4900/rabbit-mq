package com.chen.rabbitmq.dead;

import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 发送者
 * @Author: ChenChen
 * @Date: 2024-02-04 10:55
 * @Version: 1.0
 **/
@Component
public class DeadMessageSender {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendMsg(Object message){
        rabbitTemplate.convertAndSend(DeadLetterRabbitConfig.ORDER_EXCHANGE, DeadLetterRabbitConfig.ORDER_ROUTING_KEY, message, config -> {
            // 设置消息过期时间 10秒过期    如果过期时间内还没有被消费 就会发送给死信队列
            config.getMessageProperties().setExpiration("10000");
            return config;
        });
    }
}

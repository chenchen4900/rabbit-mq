package com.chen.rabbitmq.direct;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 发送者
 * @Author: ChenChen
 * @Date: 2024-02-01 16:43
 * @Version: 1.0
 **/
@Component
public class DirectSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object message){
        amqpTemplate.convertAndSend("hello", message);
    }
}

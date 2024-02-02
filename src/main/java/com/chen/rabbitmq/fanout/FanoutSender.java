package com.chen.rabbitmq.fanout;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 发送者
 * @Author: ChenChen
 * @Date: 2024-02-02 09:45
 * @Version: 1.0
 **/
@Component
public class FanoutSender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void send(Object message){
        //receiver A B C都会同时收到消息， C1 和 C2交替接收
        amqpTemplate.convertAndSend("fanoutExchange", "", message);// 发送端的 routing_key 写任何字符都会被忽略
    }

}

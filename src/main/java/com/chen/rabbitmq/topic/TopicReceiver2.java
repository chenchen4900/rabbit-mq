package com.chen.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 接收者2
 * @Author: ChenChen
 * @Date: 2024-02-01 17:41
 * @Version: 1.0
 **/
@Component
@RabbitListener(queues = "topic.user")
public class TopicReceiver2 {

    @RabbitHandler
    public void process(Object message){
        //收到 userInfo 消息
        System.out.println("Receive message: "+ message);
    }
}

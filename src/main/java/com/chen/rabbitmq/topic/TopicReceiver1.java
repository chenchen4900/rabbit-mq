package com.chen.rabbitmq.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 接收者
 * @Author: ChenChen
 * @Date: 2024-02-01 17:34
 * @Version: 1.0
 **/
@Component
@RabbitListener(queues = "topic.queue")
public class TopicReceiver1 {

    @RabbitHandler
    public void process(Object message){
        //可以收到 userInfo productInfo 两类消息
        System.out.println("Receive message: "+ message);
    }
}

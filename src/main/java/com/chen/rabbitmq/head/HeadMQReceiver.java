package com.chen.rabbitmq.head;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 接收者
 * @Author: ChenChen
 * @Date: 2024-02-04 13:53
 * @Version: 1.0
 **/
@Component
@RabbitListener(queues = "head.queue")
public class HeadMQReceiver {


    @RabbitHandler
    public void process(String content, Channel channel, Message message){
        System.out.println(content);
    }
}

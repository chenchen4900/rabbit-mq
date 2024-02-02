package com.chen.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 接收者
 * @Author: ChenChen
 * @Date: 2024-02-01 16:46
 * @Version: 1.0
 **/
@Component
@RabbitListener(queues = "hello")
public class DirectReceiver {

    @RabbitHandler
    public void process(Object message){
        System.out.println("Receive : "+ message);
    }
}

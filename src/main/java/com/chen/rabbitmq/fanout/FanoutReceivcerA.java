package com.chen.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 接收者A
 * @Author: ChenChen
 * @Date: 2024-02-02 09:47
 * @Version: 1.0
 **/
@RabbitListener(queues = "fanout.A")
@Component
public class FanoutReceivcerA {

    @RabbitHandler
    public void process(Object message){
        System.out.println("ReceiveA message: "+ message);
    }
}

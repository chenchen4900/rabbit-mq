package com.chen.rabbitmq.fanout;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 接收者B
 * @Author: ChenChen
 * @Date: 2024-02-02 09:47
 * @Version: 1.0
 **/
@RabbitListener(queues = "fanout.C")
@Component
public class FanoutReceivcerC1 {

    @RabbitHandler
    public void process(Object message){
        System.out.println("ReceiveC1 message: "+ message);
    }
}

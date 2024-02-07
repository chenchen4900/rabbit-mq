package com.chen.rabbitmq.dead;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description: 接收者
 * @Author: ChenChen
 * @Date: 2024-02-04 10:55
 * @Version: 1.0
 **/
@Component
public class DeadMessageReceiver {

    @RabbitListener(queues = "order.queue")
    @RabbitHandler
    public void handleOrderMQ(String msg){
        System.out.println("收到消息： "+ msg);
        //开启自动确认， 报错3次， 进入死信队列
        throw new RuntimeException("消费者抛出异常");
    }

    /**
     *
     * @param msg
     */
    @RabbitListener(queues = "dead.queue")
    @RabbitHandler
    public void handleDeadMQ(String msg){
        System.out.println("收到死信消息： "+ msg);
    }
}

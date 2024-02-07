package com.chen.rabbitmq.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: 生产者Confirm回调方法
 * 消息只要被 RabbitMQ broker 接收到就会触发confirm方法。
 * @Author: ChenChen
 * @Date: 2024-02-02 16:21
 * @Version: 1.0
 **/
@Component
public class ConfirmCallBackConfig implements RabbitTemplate.ConfirmCallback {
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack){
            System.out.println(correlationData.toString() + "发送成功");
        }else {
            System.out.println(correlationData.toString() + "发送失败， 原因： " + cause);
        }
    }
}

package com.chen.rabbitmq.callback;

import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description: 生产者Return回调方法
 *  如果消息未能投递到目标 queue 里将触发returnedMessage方法
 * @Author: ChenChen
 * @Date: 2024-02-02 16:42
 * @Version: 1.0
 **/
@Component
public class ReturnCallbackConfig implements RabbitTemplate.ReturnsCallback {
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        String correlationId = returnedMessage.getMessage().getMessageProperties().getMessageId();
        String msg = new String(returnedMessage.getMessage().getBody());
        System.out.println("[消息ID："+correlationId+" ,内容:"+ msg+"]发送失败, 应答码："+returnedMessage.getReplyCode()+" 原因："+returnedMessage.getReplyText()+" 交换机: "+returnedMessage.getExchange()+"  路由键: "+returnedMessage.getRoutingKey());
    }
}

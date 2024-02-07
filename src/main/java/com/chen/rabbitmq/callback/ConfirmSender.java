package com.chen.rabbitmq.callback;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static java.lang.Thread.sleep;

/**
 * @Description: 生产者发布消息确认
 *
 * @Author: ChenChen
 * @Date: 2024-02-02 16:18
 * @Version: 1.0
 **/
@Component
public class ConfirmSender {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    ConfirmCallBackConfig confirmCallBackConfig;
    @Autowired
    ReturnCallbackConfig returnCallbackConfig;

    //存储所有发送的消息
    Map<String, Object> messageMap = new HashMap();

    /**
     *  SIMPLE模式
     *  需要配置 publisher-confirm-type : simple
     * @param message
     */
    public void simpleSend(Object message){
        //设置全局唯一的id,以区分不同消息,避免ack冲突
        String id = UUID.randomUUID().toString();
        messageMap.put(id, message);

        rabbitTemplate.setConfirmCallback(confirmCallBackConfig);
        rabbitTemplate.setReturnsCallback(returnCallbackConfig);
        rabbitTemplate.invoke(action -> {
            rabbitTemplate.convertAndSend("topicExchange", "topic.message", message, new CorrelationData(id));
            return rabbitTemplate.waitForConfirms(1000L);
        });
    }

    /**
     *  CORRELATED模式
     *  需要配置 publisher-confirm-type : correlated
     * @param message
     */
    public void correlatedSend(Object message) throws InterruptedException {
        //设置全局唯一的id,以区分不同消息,避免ack冲突
        String id = UUID.randomUUID().toString();
        messageMap.put(id, message);

        RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack){
                    System.out.println(correlationData.toString() + "发送成功");
                }else {
                    System.out.println(correlationData.toString() + "发送失败， 原因： " + cause);
                }
            }
        };

        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnCallbackConfig);
        rabbitTemplate.convertAndSend("topicExchange", "topic.message", message, new CorrelationData(id));
        sleep(1000*60);
        System.out.println("发送消息boot mq hello Direct成功");
    }
}


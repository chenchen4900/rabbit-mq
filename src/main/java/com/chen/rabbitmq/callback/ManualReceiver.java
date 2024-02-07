package com.chen.rabbitmq.callback;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Description: 接收者 手动消息确认
 *  需要配置acknowledge-mode: manual
 * @Author: ChenChen
 * @Date: 2024-02-02 16:59
 * @Version: 1.0
 **/
@Component
public class ManualReceiver {

    /**
     * basicAck 表示成功确认，使用此回执方法后，消息会被RabbitMQ broker 删除
     * @param content
     * @param channel
     * @param message
     * @throws IOException
     */
    @RabbitListener(queues = "topic.message1")
    @RabbitHandler
    public void basicAck(String content, Channel channel, Message message) throws IOException {
        System.out.println("Receive message: "+ message);
        /**
         * deliveryTag 消息投递序号/消息唯一编号  multiple 是否批量确认
         */
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
    }

    /**
     * basicNack 表示失败确认，一般在消费消息业务异常时用到此方法，可以将消息重新投递入队列
     * @param message
     */
    @RabbitListener(queues = "topic.message2")
    @RabbitHandler
    public void basicNack(String content, Channel channel, Message message) throws IOException {
        try{
            System.out.println("Receive message: "+ message);
        }catch (Exception e){
            /**
             * deliveryTag 消息投递序号/消息唯一编号
             * multiple 是否批量确认
             * requeue 值为 true 消息将重新入队列
             */
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), true, true);
        }
    }

    /**
     * basicReject 拒绝消息，与basicNack区别在于不能进行批量操作，其他用法很相似
     * @param content
     * @param channel
     * @param message
     * @throws IOException
     */
    @RabbitListener(queues = "topic.message3")
    @RabbitHandler
    public void basicReject(String content, Channel channel, Message message) throws IOException {
        try{
            System.out.println("Receive message: "+ message);
        }catch (Exception e){
            /**
             * deliveryTag 消息投递序号/消息唯一编号
             * requeue 值为 true 消息将重新入队列
             */
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), true);
        }

    }

}

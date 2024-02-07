package com.chen.rabbitmq.head;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Head模式配置
 * @Author: ChenChen
 * @Date: 2024-02-04 11:21
 * @Version: 1.0
 **/
@Configuration
public class HeadRabbitConfig {

    /**
     * 定义队列
     *      topic.queue 队列名 true 持久化
     * @return
     */
    @Bean
    public Queue headQueue(){
        return new Queue("head.queue", true);
    }

    /**
     * 创建Topic类型交换机
     *      topicExchange交换机名 true 持久化  false不自动删除
     * @return
     */
    @Bean
    public HeadersExchange headExchange(){
        return new HeadersExchange("headExchange", true, false);
    }


    /**
     *  绑定队列和交换机
     *
     * @return
     */
    @Bean
    public Binding bindingHead(Queue headQueue, HeadersExchange headExchange){
        Map map = new HashMap();
        map.put("attr1", "A");
        map.put("attr2", "B");
//        whereAll 表示全部匹配
//        return BindingBuilder.bind(headQueue).to(headExchange).whereAll(map).match();
//        whereAny 表示部分匹配
        return BindingBuilder.bind(headQueue).to(headExchange).whereAny(map).match();
    }

}

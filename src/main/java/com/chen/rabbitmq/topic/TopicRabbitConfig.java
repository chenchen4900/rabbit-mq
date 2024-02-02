package com.chen.rabbitmq.topic;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Topic类型配置
 * @Author: ChenChen
 * @Date: 2024-02-01 16:49
 * @Version: 1.0
 **/
@Configuration
public class TopicRabbitConfig {


    /**
     * 定义队列
     *      topic.queue 队列名 true 持久化
     * @return
     */
    @Bean
    public Queue topicQueue(){
        return new Queue("topic.queue", true);
    }

    /**
     * 定义队列
     *      userQueue 队列名 false 不持久化
     * @return
     */
    @Bean
    public Queue userQueue(){
        return new Queue("topic.user", false);
    }


    /**
     * 创建Topic类型交换机
     *      topicExchange交换机名 true 持久化  false不自动删除
     * @return
     */
    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange", true, false);
    }


    /**
     *  绑定队列和交换机
     *
     * @return
     */
    @Bean
    public Binding bindingTopic(Queue topicQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(topicQueue).to(topicExchange).with("topic.#");
    }

    /**
     *  绑定队列和交换机
     *
     * @return
     */
    @Bean
    public Binding bindingDirect(Queue userQueue, TopicExchange topicExchange){
        return BindingBuilder.bind(userQueue).to(topicExchange).with("topic.user");
    }
}

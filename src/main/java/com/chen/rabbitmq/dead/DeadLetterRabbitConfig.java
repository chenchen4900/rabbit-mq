package com.chen.rabbitmq.dead;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 死信队列配置
 * @Author: ChenChen
 * @Date: 2024-02-04 10:43
 * @Version: 1.0
 **/
@Configuration
public class DeadLetterRabbitConfig {

    public static final String DEAD_EXCHANGE = "dead";

    public static final String ORDER_EXCHANGE = "order";

    public static final String DEAD_QUEUE = "dead.queue";

    public static final String ORDER_QUEUE = "order.queue";

    public static final String DEAD_ROUTING_KEY = "dead.message";

    public static final String ORDER_ROUTING_KEY = "order.message";

    /**
     * 声明死信交换机
     * @return DirectExchange
     */
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(DEAD_EXCHANGE);
    }

    /**
     * 声明死信队列
     * @return Queue
     */
    @Bean
    public Queue deadQueue() {
        return new Queue(DEAD_QUEUE);
    }

    /**
     * 声明订单业务交换机
     * @return DirectExchange
     */
    @Bean
    public DirectExchange orderExchange() {
        return new DirectExchange(ORDER_EXCHANGE);
    }

    /**
     * 声明订单队列
     * @return Queue
     */
    @Bean
    public Queue orderQueue() {
        // 订单队列绑定我们的死信交换机
        Map<String, Object> arguments = new HashMap<>(2);
        //死信交换机
        arguments.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //死信队列
        arguments.put("x-dead-letter-routing-key", DEAD_ROUTING_KEY);
        return new Queue(ORDER_QUEUE, true, false, false, arguments);
    }

    /**
     * 绑定死信队列到死信交换机
     * @return Binding
     */
    @Bean
    public Binding binding(Queue deadQueue,DirectExchange deadExchange) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with(DEAD_ROUTING_KEY);
    }


    /**
     * 绑定订单队列到订单交换机
     * @return Binding
     */
    @Bean
    public Binding orderBinding(Queue orderQueue, DirectExchange orderExchange) {
        return BindingBuilder.bind(orderQueue).to(orderExchange).with(ORDER_ROUTING_KEY);
    }

}

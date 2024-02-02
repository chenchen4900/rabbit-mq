package com.chen.rabbitmq.direct;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: Direct类型的交换队列配置
 * @Author: ChenChen
 * @Date: 2024-02-01 16:41
 * @Version: 1.0
 **/
@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue directQueue(){
        return new Queue("hello");
    }

}

package com.chen.rabbitmq.fanout;

import com.sun.tools.internal.xjc.reader.xmlschema.BindGreen;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: 广播类型配置
 * @Author: ChenChen
 * @Date: 2024-02-02 09:35
 * @Version: 1.0
 **/
@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue AQueue(){
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BQueue(){
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CQueue(){
        return new Queue("fanout.C");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingA(Queue AQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(AQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingB(Queue BQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(BQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindingC(Queue CQueue, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(CQueue).to(fanoutExchange);
    }
}

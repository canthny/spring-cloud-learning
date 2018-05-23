package com.tanghao.rabbit.learn.springboot.client.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author： Canthny
 * @Description： 队列配置
 * @Date： Created in 2018/5/23 11:20
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue orderSucProceedQueue(){
        return new Queue("order_suc_proceed_queue",true,false,false,null);
    }

    @Bean
    public Queue orderSucMsgSendQueue(){
        return new Queue("order_suc_msg_send",true,false,false,null);
    }

    @Bean
    public TopicExchange orderSuccessExchange(){
        return new TopicExchange("order_suc_exchange",true,false);
    }

    @Bean
    public Binding binding1(@Qualifier("orderSuccessExchange") TopicExchange exchange, @Qualifier("orderSucProceedQueue")Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("order.suc");
    }

    @Bean
    public Binding binding2(@Qualifier("orderSuccessExchange") TopicExchange exchange, @Qualifier("orderSucMsgSendQueue")Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("order.suc");
    }
}

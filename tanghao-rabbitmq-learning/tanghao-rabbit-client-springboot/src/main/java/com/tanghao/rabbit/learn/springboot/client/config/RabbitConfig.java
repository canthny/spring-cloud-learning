package com.tanghao.rabbit.learn.springboot.client.config;

import com.tanghao.rabbit.learn.springboot.client.listener.OrderSucMsgSendListener;
import com.tanghao.rabbit.learn.springboot.client.listener.OrderSucProcessListener;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author： Canthny
 * @Description： rabbit配置
 * //TODO 如果生产者和消费者是不同的应用或服务，两边都得配置，可以考虑放入统一配置中心
 * @Date： Created in 2018/5/23 11:20
 */
@Configuration
public class RabbitConfig {

    /**
     * 初始化订单处理队列bean
     * @return
     */
    @Bean
    public Queue orderSucProceedQueue(){
        return new Queue("order_suc_proceed_queue",true,false,false,null);
    }

    /**
     * 初始化订单成功短信发送队列bean
     * @return
     */
    @Bean
    public Queue orderSucMsgSendQueue(){
        return new Queue("order_suc_msg_send_queue",true,false,false,null);
    }

    /**
     * 初始化订单成功exchange
     * @return
     */
    @Bean
    public TopicExchange orderSuccessExchange(){
        return new TopicExchange("order_suc_exchange",true,false);
    }

    /**
     * 声明绑定关系
     * //TODO 绑定的动作在什么时候做？
     * @param exchange
     * @param queue
     * @return
     */
    @Bean
    public Binding binding1(@Qualifier("orderSuccessExchange") TopicExchange exchange, @Qualifier("orderSucProceedQueue")Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("order.suc");
    }

    @Bean
    public Binding binding2(@Qualifier("orderSuccessExchange") TopicExchange exchange, @Qualifier("orderSucMsgSendQueue")Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("order.suc");
    }

    @Bean
    public SimpleMessageListenerContainer orderSucMsgSendMessageContainer(CachingConnectionFactory cachingConnectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setQueues(orderSucMsgSendQueue());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new OrderSucMsgSendListener());
        return container;
    }

    @Bean
    public SimpleMessageListenerContainer orderSucProcessMessageContainer(CachingConnectionFactory cachingConnectionFactory){
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
        container.setQueues(orderSucProceedQueue());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setMessageListener(new OrderSucProcessListener());
        return container;
    }
}

package com.tanghao.rabbit.learn.springboot.client.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.tanghao.rabbit.learn.springboot.client.domain.OrderSucMsg;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author： Canthny
 * @Description： 订单成功后续处理消费者
 * @Date： Created in 2018/5/23 16:55
 */
@Component
public class OrderSucProcessConsumer {

//    @RabbitListener(queues = {"order_suc_proceed_queue"})
//    public void process(String content) {
//        System.out.println("consumer listener get:"+content);
//    }

//    @RabbitListener(queues = {"order_suc_proceed_queue"})
//    /**
//     * obj对象必须和消息发送传入的对象类型一致，否则报错
//     */
//    public void process(OrderSucMsg obj) {
//        System.out.println("consumer listener get:"+obj.getPayOrderNo()+"|"+obj.getStatus()+"|"+obj.getSucTime());
//    }

//    @RabbitListener(queues = {"order_suc_proceed_queue"})
    /**
     * //TODO 这种写法会用Spring默认的Consumer会设置ACKmode为auto，这个如何改成manual让消费端手动确认，否者这里再去确认会报错:precondition_failed unknown delivery tag 1
     */
    public void on(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println(new String(message.getBody()));
    }
}

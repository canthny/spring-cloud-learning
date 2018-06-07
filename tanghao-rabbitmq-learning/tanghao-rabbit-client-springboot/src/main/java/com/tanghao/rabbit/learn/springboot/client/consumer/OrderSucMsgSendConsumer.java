package com.tanghao.rabbit.learn.springboot.client.consumer;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author： Canthny
 * @Description： 订单处理成功短信发送消费者
 * @Date： Created in 2018/5/23 16:56
 */
@Component
public class OrderSucMsgSendConsumer {

//    @RabbitListener(queues = {"order_suc_msg_send_queue"})
//    public void on(Message message, Channel channel) throws IOException {
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        System.out.println(new String(message.getBody()));
//    }
}

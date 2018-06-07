package com.tanghao.rabbit.learn.springboot.client.listener;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * @Author： Canthny
 * @Description： 订单成功后续处理监听
 * @Date： Created in 2018/6/6 17:28
 */
public class OrderSucProcessListener implements ChannelAwareMessageListener {
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        System.out.println(new String(message.getBody()));
    }
}

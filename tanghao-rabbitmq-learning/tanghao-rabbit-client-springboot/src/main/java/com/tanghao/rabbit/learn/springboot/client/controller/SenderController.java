package com.tanghao.rabbit.learn.springboot.client.controller;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： Canthny
 * @Description： 消息发送controller
 * @Date： Created in 2018/5/23 9:55
 */
@RestController
public class SenderController {

    @Autowired
    AmqpTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;

    @RequestMapping(value = "/send")
    public String send(){
        /**
         * channel.basicPublish() MessageProperties 关键属性deliveryMode = 2时消息持久化，需要消费者ack后才会删除，spring整合的时候默认打开持久化；
         */
        rabbitTemplate.convertAndSend("order_suc_exchange","order.suc","test send msg by topic exchange");
        return "success";
    }
}

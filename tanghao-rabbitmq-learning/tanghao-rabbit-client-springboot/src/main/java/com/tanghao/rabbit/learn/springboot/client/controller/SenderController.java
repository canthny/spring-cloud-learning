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
    AmqpTemplate amqpTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;

    @RequestMapping(value = "/send")
    public String send(){
//        amqpTemplate.convertAndSend();
        return "success";
    }
}

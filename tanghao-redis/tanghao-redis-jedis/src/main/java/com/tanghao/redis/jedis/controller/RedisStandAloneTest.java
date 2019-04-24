package com.tanghao.redis.jedis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author： Canthny
 * @Description： 单机redis测试类
 * @Date： Created in 2019/3/1 17:11
 */
@RestController
public class RedisStandAloneTest {

    private static AtomicInteger count = new AtomicInteger(0);

    private final Integer MAX = 999999999;

    @Autowired
    RedisTemplate redisTemplate;

    @RequestMapping(value = "/testGet",method = RequestMethod.POST)
    public String testGet(){
//        Integer  random = new Random().nextInt(MAX);
//        redisTemplate.opsForValue().get(random);
        return "success";
    }

    @RequestMapping(value = "/testSet",method = RequestMethod.POST)
    public String testSet(){
        Integer key = count.incrementAndGet();
        if(key<MAX){
            redisTemplate.opsForValue().set(key,UUID.randomUUID().toString());
        }
        return "success";
    }
}

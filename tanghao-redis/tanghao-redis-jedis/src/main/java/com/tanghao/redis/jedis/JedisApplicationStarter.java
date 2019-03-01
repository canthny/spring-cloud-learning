package com.tanghao.redis.jedis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author： Canthny
 * @Description： 启动类
 * @Date： Created in 2019/3/1 17:09
 */
@SpringBootApplication
public class JedisApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(JedisApplicationStarter.class,args);
    }
}

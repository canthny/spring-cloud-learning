package com.tanghao.springcloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Author：Canthny
 * @Description：Eureka启动
 * @Date：Created in 2018/5/7 11:58
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServerStarter{

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerStarter.class,args);
    }
}

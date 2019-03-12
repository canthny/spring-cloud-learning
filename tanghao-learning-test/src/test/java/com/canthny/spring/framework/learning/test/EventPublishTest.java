package com.canthny.spring.framework.learning.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tanghao.learning.test.DemoApplication;
import tanghao.learning.test.spring.framework.service.event.MyOwnEvent;

/**
 * @Author： Canthny
 * @Description： //TODO 那么请问：这个类是干嘛的呢？
 * @Description：
 * @Date： Created in 2019/3/5 22:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class EventPublishTest {

    @Autowired
    ApplicationContext applicationcontext;

    @Test
    public void publishEvent() throws InterruptedException {
        applicationcontext.publishEvent(new MyOwnEvent<String>(this,"hahaha my own event!"));
        Thread.sleep(10000L);
    }
}

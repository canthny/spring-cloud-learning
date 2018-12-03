package com.canthny.spring.framework.learning.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tanghao.learning.test.spring.framework.service.TestServiceA;

/**
 * @Author： Canthny
 * @Description： ClassPathXmlApplicationContext测试
 * @Date： Created in 2018/12/3 23:56
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ClassPathXmlApplicationContextTest {

    @Test
    public void test(){
        BeanFactory factory = new ClassPathXmlApplicationContext("testXmlBeanFactory.xml");
        TestServiceA testService = (TestServiceA) factory.getBean("testServiceA");
        testService.test();
    }
}

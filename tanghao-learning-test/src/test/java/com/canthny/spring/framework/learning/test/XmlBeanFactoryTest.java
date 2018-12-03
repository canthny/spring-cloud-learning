package com.canthny.spring.framework.learning.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tanghao.learning.test.spring.framework.service.TestServiceA;

/**
 * @Author： Canthny
 * @Description： XmlBeanFactory学习
 * @Date： Created in 2018/12/3 19:55
 */
@Deprecated
@RunWith(SpringJUnit4ClassRunner.class)
public class XmlBeanFactoryTest {

    @Test
    public void test1(){
        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("testXmlBeanFactory.xml"));
        TestServiceA testService = (TestServiceA) beanFactory.getBean("testServiceA");
        testService.test();
    }
}

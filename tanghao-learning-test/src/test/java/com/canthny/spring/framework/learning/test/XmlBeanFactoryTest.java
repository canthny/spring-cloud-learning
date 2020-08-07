//package com.canthny.spring.framework.learning.test;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.xml.XmlBeanFactory;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.yaml.snakeyaml.Yaml;
//import tanghao.learning.test.spring.framework.service.TestServiceA;
//
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @Author： Canthny
// * @Description： XmlBeanFactory学习
// * @Date： Created in 2018/12/3 19:55
// */
//@Deprecated
//@RunWith(SpringJUnit4ClassRunner.class)
//public class XmlBeanFactoryTest {
//
//    @Test
//    public void test1(){
//        BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("testXmlBeanFactory.xml"));
//        //BeanFacotry延迟加载,如果Bean的某一个属性没有注入，BeanFacotry加载后，直至第一次使用调用getBean方法才会抛出异常
//        TestServiceA testService = (TestServiceA) beanFactory.getBean("testServiceA");
//        testService.test();
//    }
//
//}

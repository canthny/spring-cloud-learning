package tanghao.learning.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import org.springframework.stereotype.Component;
import tanghao.learning.test.spring.framework.service.event.MyOwnEvent;
import tanghao.learning.test.testHessian.TestHessianService;

/**
 * @Author：Canthny
 * @Description：//TODO 那么请问：这个类是干嘛的呢？
 * @Date：Created in 2018/5/8 16:13
 */
@SpringBootApplication
@ComponentScan("tanghao.learning.test.spring.framework.service.event")
public class DemoApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(DemoApplication.class,args);
        applicationContext.publishEvent(new MyOwnEvent<String>(new Object(),"hahaha my own event!"));
    }

//    @Bean
//    public HessianProxyFactoryBean helloClient() {
//        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
//        factory.setServiceUrl("http://xxx.xxx.xxx.xxx:xxxx/path/xxxxx.hessian");
//        factory.setServiceInterface(TestHessianService.class);
//        return factory;
//    }
}

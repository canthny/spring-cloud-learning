package tanghao.learning.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;
import tanghao.learning.test.testHessian.TestHessianService;

/**
 * @Author：Canthny
 * @Description：//TODO 那么请问：这个类是干嘛的呢？
 * @Date：Created in 2018/5/8 16:13
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class,args);
    }

//    @Bean
//    public HessianProxyFactoryBean helloClient() {
//        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
//        factory.setServiceUrl("http://xxx.xxx.xxx.xxx:xxxx/path/xxxxx.hessian");
//        factory.setServiceInterface(TestHessianService.class);
//        return factory;
//    }
}

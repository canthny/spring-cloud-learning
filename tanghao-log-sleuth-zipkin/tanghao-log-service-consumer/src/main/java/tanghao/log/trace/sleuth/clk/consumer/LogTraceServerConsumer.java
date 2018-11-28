package tanghao.log.trace.sleuth.clk.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;



/**
 * @Author： Canthny
 * @Description： 链路跟踪服务消费者
 * @Date： Created in 2018/6/1 9:33
 */
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
public class LogTraceServerConsumer {

    public static void main(String[] args) {
        SpringApplication.run(LogTraceServerConsumer.class);
    }
}

package tanghao.log.trace.sleuth.elk.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @Author： Canthny
 * @Description： 链路跟踪服务提供者
 * @Date： Created in 2018/6/1 9:33
 */
@EnableFeignClients
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class LogTraceServerProvider {

    public static void main(String[] args) {
        SpringApplication.run(LogTraceServerProvider.class);
    }
}

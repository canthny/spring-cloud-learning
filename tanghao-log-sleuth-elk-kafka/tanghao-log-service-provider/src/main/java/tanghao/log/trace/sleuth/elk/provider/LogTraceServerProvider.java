package tanghao.log.trace.sleuth.elk.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author： Canthny
 * @Description： 链路跟踪服务提供者
 * @Date： Created in 2018/6/1 9:33
 */
@SpringBootApplication
public class LogTraceServerProvider {

    public static void main(String[] args) {
        SpringApplication.run(LogTraceServerProvider.class);
    }
}

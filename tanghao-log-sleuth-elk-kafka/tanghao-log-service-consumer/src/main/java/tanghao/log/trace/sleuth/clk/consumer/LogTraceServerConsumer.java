package tanghao.log.trace.sleuth.clk.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author： Canthny
 * @Description： 链路跟踪服务消费者
 * @Date： Created in 2018/6/1 9:33
 */
@SpringBootApplication
public class LogTraceServerConsumer {

    public static void main(String[] args) {
        SpringApplication.run(LogTraceServerConsumer.class);
    }
}

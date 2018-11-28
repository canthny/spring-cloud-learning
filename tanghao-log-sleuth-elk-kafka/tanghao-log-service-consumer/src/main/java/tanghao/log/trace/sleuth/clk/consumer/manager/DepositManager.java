package tanghao.log.trace.sleuth.clk.consumer.manager;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import tanghao.log.trace.sleuth.clk.consumer.annotation.MicroServiceMonitor;

import java.util.Random;

/**
 * @Author： Canthny
 * @Description： 充值业务manager
 * @Date： Created in 2018/6/30 20:52
 */
@FeignClient(name = "log-trace-service-provider")
public interface DepositManager {

//    @MicroServiceMonitor(serverName = "充值服务")
    @PostMapping("/provider")
    public String deposit();
//        int max=10000;
//        int min=1000;
//        Random random = new Random();
//        Integer s = random.nextInt(max)%(max-min+1) + min;
//        try {
//            Thread.sleep(s.longValue());
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("充值服务");

//    }
}

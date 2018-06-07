package tanghao.log.trace.sleuth.clk.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author： Canthny
 * @Description： 消费者controller
 * @Date： Created in 2018/6/1 9:45
 */
@RestController
public class ConsumerController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @RequestMapping(value = "/consumer")
    public String provider(){
        logger.info("trace span in ProviderController");
        String result = restTemplate.getForObject("http://localhost:8081/provider",String.class);
        logger.info(result);
        return "consumer is done!";
    }
}

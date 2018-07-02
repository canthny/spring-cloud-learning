package tanghao.log.trace.sleuth.clk.consumer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tanghao.log.trace.sleuth.clk.consumer.manager.DepositManager;

/**
 * @Author： Canthny
 * @Description： 网关对外暴露统一入口
 * @Date： Created in 2018/6/30 20:49
 */
@RestController
public class DispatcherAction {

    @Autowired
    DepositManager depositManager;

    private static final Logger logger = LoggerFactory.getLogger(DispatcherAction.class);

    @RequestMapping(value = "/service")
    public String service(){
        depositManager.deposit();
        logger.info("service is done!");
        return "service is done!";
    }
}

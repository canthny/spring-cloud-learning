package tanghao.lcn.demo.order.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tanghao.lcn.demo.order.domain.OrderBase;

/**
 * @Author： Canthny
 * @Description： 订单controller
 * @Date： Created in 2018/5/11 9:27
 */
@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @RequestMapping(value = "/createOrder")
    public void createOrder(){
        OrderBase orderBase = new OrderBase();

    }
}

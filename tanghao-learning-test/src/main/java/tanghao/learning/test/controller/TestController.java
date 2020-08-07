package tanghao.learning.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： Canthny
 * @Description： 测试Controller
 * @Date： Created in 2018/5/8 16:24
 */
@RestController
@RequestMapping(value = "/test1")
public class TestController {

    @RequestMapping("/testAopAspect")
    @ResponseBody
    public void testAopAspect(){
//        String s = null;s.substring(1);
        System.out.println("testAopAspect method in");
    }
}

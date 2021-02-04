package tanghao.learning.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tanghao.learning.test.spring.framework.service.TestServiceA;
import tanghao.learning.test.spring.framework.service.User;

/**
 * @Author： Canthny
 * @Description： 测试Controller
 * @Date： Created in 2018/5/8 16:24
 */
@RestController
@RequestMapping(value = "/test1")
public class TestController {

    @Autowired
    TestServiceA testServiceA;

    @RequestMapping("/testAopAspect")
    @ResponseBody
    public void testAopAspect(){
//        String s = null;s.substring(1);
        User user = new User("canthny","tanghao",18);
        testServiceA.test(user);
        System.out.println("testAopAspect method in");
    }
}

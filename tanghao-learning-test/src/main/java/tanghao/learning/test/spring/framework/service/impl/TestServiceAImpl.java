package tanghao.learning.test.spring.framework.service.impl;

import org.springframework.stereotype.Service;
import tanghao.learning.test.spring.framework.service.TestServiceA;
import tanghao.learning.test.spring.framework.service.TestServiceB;
import tanghao.learning.test.spring.framework.service.User;

/**
 * @Author： Canthny
 * @Description： TestService1实现类
 * @Date： Created in 2018/12/3 20:11
 */
@Service
public class TestServiceAImpl implements TestServiceA {

    TestServiceB testServiceB;

    public TestServiceB getTestServiceB() {
        return testServiceB;
    }

    public void setTestServiceB(TestServiceB testServiceB) {
        this.testServiceB = testServiceB;
    }

    private void init(){
        System.out.println("TestServiceAImpl is init");
    }

    @Override
    public void test() {
        System.out.println("test A is success");
    }

    @Override
    public void test(User user) {
        System.out.println("test A user is success"+user);
    }
}

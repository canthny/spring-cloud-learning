package tanghao.learning.test.spring.framework.service.impl;

import tanghao.learning.test.spring.framework.service.TestServiceA;
import tanghao.learning.test.spring.framework.service.TestServiceB;

/**
 * @Author： Canthny
 * @Description： TestService1实现类
 * @Date： Created in 2018/12/3 20:11
 */
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
}

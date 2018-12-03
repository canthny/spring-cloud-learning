package tanghao.learning.test.spring.framework.service.impl;

import tanghao.learning.test.spring.framework.service.TestServiceB;

/**
 * @Author： Canthny
 * @Description： testServiceB实现类
 * @Date： Created in 2018/12/3 20:46
 */
public class TestServiceBImpl implements TestServiceB{

    private void init(){
        System.out.println("TestServiceBImpl is init");
    }

    @Override
    public void test() {
        System.out.println("test B is success");
    }
}

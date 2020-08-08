package tanghao.learning.test.java.proxy;

/**
 * Description： 代理测试类
 * Created By tanghao on 2020/8/8
 */
public class ProxyTest {

    public static void main(String[] args) {
        TestService testService = new TestServiceImpl();
        System.out.println(testService.getClass());
        TestService proxy = (TestService)new JDKProxy(testService).getProxyInstance();
        proxy.testMethod();
        System.out.println(proxy.getClass());
    }
}

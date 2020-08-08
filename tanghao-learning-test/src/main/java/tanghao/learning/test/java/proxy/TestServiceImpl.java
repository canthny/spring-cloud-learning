package tanghao.learning.test.java.proxy;

/**
 * Description： TODO
 * Created By tanghao on 2020/8/8
 */
public class TestServiceImpl implements TestService{
    @Override
    public boolean testMethod() {
        System.out.println("TestServiceImpl method");
        return false;
    }
}

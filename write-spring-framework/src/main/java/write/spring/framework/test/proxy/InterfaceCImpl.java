package write.spring.framework.test.proxy;

/**
 * Description： TODO
 * Created By tanghao on 2020/9/16
 */
public class InterfaceCImpl implements InterfaceC {

    private Integer count;

    private InterfaceA a;

    @Override
    public String toString() {
        return "InterfaceCImpl{" +
                "a=" + a +
                '}';
    }

    @Override
    public void print() {
        System.out.println("count="+count);
    }
}

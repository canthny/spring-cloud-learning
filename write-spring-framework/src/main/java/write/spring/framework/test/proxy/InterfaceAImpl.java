package write.spring.framework.test.proxy;

/**
 * Description： TODO
 * Created By tanghao on 2020/9/16
 */
public class InterfaceAImpl implements InterfaceA {
    private Integer a = 1;
    @Override
    public void A() {
        System.out.println("A in method A" + a);
    }

    public Integer getA(){
        return a;
    }

    @Override
    public String toString() {
        return "A{" +
                "a=" + a +
                '}';
    }
}

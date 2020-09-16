package write.spring.framework.util;

/**
 * Description： TODO
 * Created By tanghao on 2020/9/16
 */
public class ClassB {

    private InterfaceA a;

    public InterfaceA getA(){
        return a;
    }

    @Override
    public String toString() {
        return "B{" +
                "a=" + a +
                '}';
    }
}

package write.spring.framework.bean.ioc;

import write.spring.framework.annotation.Bean;
import write.spring.framework.annotation.InitMethod;
import write.spring.framework.annotation.Injection;

/**
 * Description： 测试bean A
 * Created By tanghao on 2020/8/1
 */
@Bean(name = "componentA")
public class ComponentA {

    private final String name = "A";

    private int a;

    public ComponentA(){
        System.out.println("A instantiate in");
    }


    @Injection
    ComponentB componentB;

    public void test(){
        System.out.println("ComponentA test in");
        System.out.println("componentB="+this);
        System.out.println("componentB="+componentB);
    }

    @InitMethod
    private void testInitMethod(){
        System.out.println("ComponentA initMethod in");
    }

    @Override
    public String toString() {
        return "ComponentA{" +
                "name='" + name + '\'' +
                ", a=" + a +
                ", componentB=" + (componentB == null) +
                '}';
    }
}

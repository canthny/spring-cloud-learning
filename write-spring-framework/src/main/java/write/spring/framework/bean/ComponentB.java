package write.spring.framework.bean;

import write.spring.framework.annotation.Bean;
import write.spring.framework.annotation.InitMethod;
import write.spring.framework.annotation.Injection;

/**
 * Description： TODO
 * Created By tanghao on 2020/8/1
 */
@Bean(name = "componentB")
public class ComponentB {

    private final String name = "B";

    private int b;

    public ComponentB(){
        System.out.println("B instantiate in");
    }

    @Injection
    ComponentA componentA;

    @InitMethod
    private void testInitMethod(){
        System.out.println("ComponentB initMethod in");
    }


    @Override
    public String toString() {
        return "ComponentB{" +
                "name='" + name + '\'' +
                ", b=" + b +
                ", componentA=" + componentA +
                '}';
    }
}

package write.spring.framework.test;

import write.spring.framework.application.BeanApplication;
import write.spring.framework.bean.ioc.ComponentA;
import write.spring.framework.bean.ioc.ComponentB;

/**
 * Description： IOC测试类
 * Created By tanghao on 2020/8/1
 */
public class IocTestStarter {

    public static void main(String[] args) throws ClassNotFoundException {
        BeanApplication beanApplication = new BeanApplication();
        beanApplication.run();
        ComponentA componentA = beanApplication.getBean("componentA", ComponentA.class);
        System.out.println(componentA);
        ComponentB componentB = beanApplication.getBean("componentB", ComponentB.class);
        System.out.println(componentB);
    }
}

package write.spring.framework.test;

import write.spring.framework.application.BeanApplication;
import write.spring.framework.bean.aop.IServiceC;
import write.spring.framework.bean.aop.IServiceD;

/**
 * Description： aop测试
 * Created By tanghao on 2020/8/8
 */
public class AopTestStarter {

    public static void main(String[] args) throws ClassNotFoundException {
        BeanApplication beanApplication = new BeanApplication();
        beanApplication.run();
        IServiceC iServiceC = beanApplication.getBean("iServiceC", IServiceC.class);
        System.out.println(iServiceC);
        iServiceC.cMethod();
        IServiceD iServiceD = beanApplication.getBean("iServiceD", IServiceD.class);
        System.out.println(iServiceD);
        iServiceD.dMethod();
    }
}

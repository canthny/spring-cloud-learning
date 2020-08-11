package write.spring.framework.bean;

import write.spring.framework.annotation.*;
import write.spring.framework.domain.AopMethodInvoker;

/**
 * Description： TODO
 * Created By tanghao on 2020/8/8
 */
@Bean(name = "aspectServiceC")
@Aspect
public class AspectServiceC {

    @Before(clazz = "write.spring.framework.bean.aop.ServiceC",method = "cMethod")
    public void beforeCMethod(){
        System.out.println("AspectServiceC beforeCmethod is in");
    }

    @Around(clazz = "write.spring.framework.bean.aop.ServiceC",method = "cMethod")
    public Object aroundCMethod(AopMethodInvoker aopMethodInvoker){
        System.out.println("AspectServiceC aroundCMethod is in");
        Object res = aopMethodInvoker.invoker();
        System.out.println("AspectServiceC aroundCMethod is out");
        return res;
    }

    @After(clazz = "write.spring.framework.bean.aop.ServiceC",method = "cMethod")
    public void afterCMethod(){
        System.out.println("AspectServiceC afterCMethod is in");
    }
}

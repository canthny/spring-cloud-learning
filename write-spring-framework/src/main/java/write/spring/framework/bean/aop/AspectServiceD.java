package write.spring.framework.bean.aop;

import write.spring.framework.annotation.*;
import write.spring.framework.domain.AopMethodInvoker;

/**
 * Description： TODO
 * Created By tanghao on 2020/8/8
 */
@Bean(name = "aspectServiceD")
@Aspect
public class AspectServiceD {

    @Before(clazz = "write.spring.framework.bean.aop.ServiceD",method = "dMethod")
    public void beforeDMethod(){
        System.out.println("AspectServiceD beforeDmethod is in");
    }

    @Around(clazz = "write.spring.framework.bean.aop.ServiceD",method = "dMethod")
    public Object aroundDMethod(AopMethodInvoker aopMethodInvoker){
        System.out.println("AspectServiceD aroundDMethod is in");
        Object res = aopMethodInvoker.invoker();
        System.out.println("AspectServiceD aroundDMethod is out");
        return res;
    }

    @After(clazz = "write.spring.framework.bean.aop.ServiceD",method = "dMethod")
    public void afterDMethod(){
        System.out.println("AspectServiceD afterDMethod is in");
    }
}

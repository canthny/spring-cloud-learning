package write.spring.framework.bean;

import write.spring.framework.annotation.Aspect;
import write.spring.framework.annotation.Bean;
import write.spring.framework.annotation.Before;

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
}

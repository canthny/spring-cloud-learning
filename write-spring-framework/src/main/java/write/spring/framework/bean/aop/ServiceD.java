package write.spring.framework.bean.aop;

import write.spring.framework.annotation.Bean;
import write.spring.framework.annotation.Injection;

/**
 * Description： TODO
 * Created By tanghao on 2020/8/8
 */
@Bean(name = "iServiceD")
public class ServiceD implements IServiceD{

    @Injection
    IServiceC iServiceC;

    @Override
    public boolean dMethod() {
        System.out.println("ServiceD dMethod in");
        return false;
    }

    @Override
    public boolean dMethodWithParam(String param) {
        System.out.println("ServiceD dMethodWithParam in, param=" + param);
        return false;
    }
}

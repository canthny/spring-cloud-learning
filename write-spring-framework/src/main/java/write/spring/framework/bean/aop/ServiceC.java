package write.spring.framework.bean.aop;

import write.spring.framework.annotation.Bean;

/**
 * Description： Aop测试Bean
 * Created By tanghao on 2020/8/8
 */
@Bean(name = "iServiceC")
public class ServiceC implements IServiceC{
    @Override
    public boolean cMethod() {
        System.out.println("ServiceC cMethod in");
        return false;
    }

    @Override
    public boolean cMethodWithParam(String param) {
        System.out.println("ServiceC cMethodWithParam in, param=" + param);
        return false;
    }
}

package tanghao.learning.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Author： Canthny
 * @Description： Aop Aspect demo测试类
 * @Date： Created in 2018/5/8 15:12
 */
@Component
@Aspect
public class AspectDemo {
    @Before(value = "execution(* tanghao.learning.test.spring.framework.service.circle.dependency.A.getName())")
    public void before(JoinPoint joinPoint) {
        System.out.println("AspectDemo in Before method");
    }

    @Around(value = "execution(* tanghao.learning.test.spring.framework.service.circle.dependency.B.getName())")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("AspectDemo in Around method 1");
        pjp.proceed();
        System.out.println("AspectDemo in Around method 2");
    }

    @After(value = "execution(* tanghao.learning.test.controller.TestController.testAopAspect())")
    public void after(JoinPoint joinPoint) {
        System.out.println("AspectDemo in After method");
    }

    @AfterReturning(value = "execution(* tanghao.learning.test.controller.TestController.testAopAspect())")
    public void afterReturning(JoinPoint joinPoint) {
        System.out.println("AspectDemo in AfterReturning method");
    }

    @AfterThrowing(value = "execution(* tanghao.learning.test.controller.TestController.testAopAspect())")
    public void afterThrowing(JoinPoint joinPoint) {
        System.out.println("AspectDemo in AfterThrowing method");
    }
}

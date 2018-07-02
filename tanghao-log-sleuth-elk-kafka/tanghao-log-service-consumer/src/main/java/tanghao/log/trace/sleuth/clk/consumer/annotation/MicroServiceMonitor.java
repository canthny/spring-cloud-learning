package tanghao.log.trace.sleuth.clk.consumer.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author： Canthny
 * @Description： 微服务监控驱动注解
 * @Date： Created in 2018/6/30 21:11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MicroServiceMonitor {

     String serverName() default  "";
}

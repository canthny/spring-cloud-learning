package tanghao.learning.test.spring.framework.service.circle.dependency;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Description： TODO
 * Created By tanghao on 2020/7/15
 */
public class TestCircleDependency {

    public static void main(String[] args) {
        BeanFactory factory = new AnnotationConfigApplicationContext("tanghao.learning.test.spring.framework.service.circle.dependency");
        A a = factory.getBean("a",A.class);
        System.out.println(a);
    }
}

package write.spring.framework.application;

import write.spring.framework.domain.BeanDefinition;
import write.spring.framework.factory.DefaultBeanFactory;
import write.spring.framework.service.ResourceLoader;

import java.util.List;

/**
 * Description： 应用启动上下文
 * Created By tanghao on 2020/8/1
 */
public class BeanApplication {

    private static DefaultBeanFactory defaultBeanFactory = new DefaultBeanFactory();

    public void run() throws ClassNotFoundException {
        String[] packages = {"write.spring.framework.bean"};
        List<BeanDefinition> beanDefinitions = ResourceLoader.getAllBeans(packages);
        if(beanDefinitions==null || beanDefinitions.size()<1){
            System.out.println("can not find beans in packages="+packages);
            return;
        }
        for(BeanDefinition definition : beanDefinitions){
            defaultBeanFactory.registryBeanDefinition(definition);
        }
        defaultBeanFactory.checkBeanBefore();

        defaultBeanFactory.refresh();
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return defaultBeanFactory.getBeanByName(name,clazz);
    }



}

package write.spring.framework.factory;

import write.spring.framework.domain.BeanDefinition;

/**
 * Description： TODO
 * Created By tanghao on 2020/8/1
 */
public interface BeanFactory {

    <T> T getBeanByName(String name, Class<T> clazz);

    Object getBeanByName(String name);

    void registryBeanDefinition(BeanDefinition beanDefinition);

    void refresh();
}

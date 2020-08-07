package write.spring.framework.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import write.spring.framework.annotation.Injection;
import write.spring.framework.domain.BeanDefinition;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description： 默认容器
 * Created By tanghao on 2020/8/1
 */
public class DefaultBeanFactory implements BeanFactory {

    Logger logger = LoggerFactory.getLogger(DefaultBeanFactory.class);

    private Map<String,Object> beanObjectMap = new ConcurrentHashMap<>();

    private Map<String,Object> beanInstancedMap = new ConcurrentHashMap<>();

    private Map<String,BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    @Override
    public <T> T getBeanByName(String name, Class<T> clazz) {
        Object bean = getBeanByName(name);
        if(bean != null && clazz.isInstance(bean)){
            return clazz.cast(beanObjectMap.get(name));
        }
        logger.error("can not find bean name:" + name + " and class=" + clazz.getName());
        return null;
    }

    @Override
    public Object getBeanByName(String name) {
        Object bean = beanObjectMap.get(name);
        if(bean != null){
            return bean;
        }
        try {
            bean = createBean(name);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public void registryBeanDefinition(BeanDefinition beanDefinition) {
        String name = beanDefinition.getName();
        if(beanDefinitionMap.get(name) == null){
            beanDefinitionMap.put(name,beanDefinition);
        }else{
            logger.error("bean name is existed, can not create 2 beans with the same name!");
        }
    }

    @Override
    public void refresh() {
        for(Map.Entry<String,BeanDefinition> entry:beanDefinitionMap.entrySet()){
            String beanName = entry.getKey();
            Object bean = getBeanByName(beanName);
        }
    }

    private Object createBean(String name) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, Exception {
        BeanDefinition beanDefinition = beanDefinitionMap.get(name);
        Class beanClass = beanDefinition.getClazz();
        Object bean = beanClass.newInstance();
        beanInstancedMap.put(name,bean);

        //初始化属性
        Field[] fields = beanClass.getDeclaredFields();
        for(Field field:fields){
            Injection injection = field.getAnnotation(Injection.class);
            if (null != injection) {
                field.setAccessible(true);
                Object innerBean = beanInstancedMap.get(field.getName());
                if(innerBean == null){
                    innerBean = getBeanByName(field.getName());
                }
                if(innerBean == null) {
                    logger.error("get inner bean fail");
                    throw new Exception("get inner bean fail");
                }
                field.set(bean,innerBean);
            }
        }
        //初始化方法
        if(null!=beanDefinition.getInitMethod()){
            Method initMethod = beanClass.getDeclaredMethod(beanDefinition.getInitMethod(),null);
            initMethod.setAccessible(true);
            initMethod.invoke(bean);
        }

        //全部处理成功后存缓存
        beanObjectMap.put(name,bean);
        return bean;
    }
}

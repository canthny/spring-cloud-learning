package write.rpc.core.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单存放bean
 */
public class BeanFactory {

    private static final Map<String,Object> beanMap = new HashMap<>();

    public static void put(String beanId,Object beanObj){
        beanMap.put(beanId,beanObj);
    }

    public static Object get(String beanId){
        return beanMap.get(beanId);
    }
}

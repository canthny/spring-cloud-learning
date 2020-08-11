package write.spring.framework.domain;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Description： TODO
 * Created By tanghao on 2020/8/11
 */
public class AopMethodInvoker {

    private Method method;

    private Object obj;

    private Object[] args;

    public AopMethodInvoker(Method method,Object target,Object... args){
        this.method = method;
        this.obj = target;
        this.args = args;
    }

    public Object invoker(){
        try {
            return method.invoke(obj,args);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package write.spring.framework.util;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.util.StringUtils;
import write.spring.framework.domain.AopDefinition;
import write.spring.framework.domain.AopMethodInvoker;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description： TODO
 * Created By tanghao on 2020/9/18
 */
public class CglibAopProxy implements MethodInterceptor {

    private Object obj;

    private Object aspectObj;

    private AopDefinition aopDefinition;

    public CglibAopProxy(Object obj, Object aspectObj, AopDefinition aopDefinition){
        this.obj = obj;
        this.aspectObj = aspectObj;
        this.aopDefinition = aopDefinition;
    }

    public Object getProxyInstance(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(obj.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Object result = null;
        //pre
        if(method.getName().equals(aopDefinition.getAopMethodName())){
            if(!StringUtils.isEmpty(aopDefinition.getBeforeMethod())){
                Method beforeMethod = aspectObj.getClass().getDeclaredMethod(aopDefinition.getBeforeMethod());
                beforeMethod.invoke(aspectObj);
            }

            if(StringUtils.isEmpty(aopDefinition.getAroundMethod())){
                result = method.invoke(obj, args);
            }else{
                Method aroundMethod = aspectObj.getClass().getDeclaredMethod(aopDefinition.getAroundMethod(), AopMethodInvoker.class);
                result = aroundMethod.invoke(aspectObj,new AopMethodInvoker(method,obj,args));
            }

            if(!StringUtils.isEmpty(aopDefinition.getAfterMethod())){
                Method afterMethod = aspectObj.getClass().getDeclaredMethod(aopDefinition.getAfterMethod());
                afterMethod.invoke(aspectObj);
            }
        }else{
            result = method.invoke(obj, args);
        }

        return result;
    }
}

package write.spring.framework.util;

import org.springframework.util.StringUtils;
import write.spring.framework.domain.AopDefinition;
import write.spring.framework.domain.AopMethodInvoker;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description： jdk动态代理
 * Created By tanghao on 2020/8/8
 */
public class JDKProxy {

    private Object obj;

    private Object aspectObj;

    private AopDefinition aopDefinition;

    public JDKProxy(Object obj, Object aspectObj, AopDefinition aopDefinition){
        this.obj = obj;
        this.aspectObj = aspectObj;
        this.aopDefinition = aopDefinition;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),new JdkInvocationHandler());
    }

    class JdkInvocationHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
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
                    Method aroundMethod = aspectObj.getClass().getDeclaredMethod(aopDefinition.getAroundMethod(),AopMethodInvoker.class);
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
}

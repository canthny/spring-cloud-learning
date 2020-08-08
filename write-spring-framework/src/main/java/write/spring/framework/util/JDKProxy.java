package write.spring.framework.util;

import write.spring.framework.domain.ProxyDefinition;

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

    private ProxyDefinition proxyDefinition;

    public JDKProxy(Object obj, Object aspectObj, ProxyDefinition proxyDefinition){
        this.obj = obj;
        this.aspectObj = aspectObj;
        this.proxyDefinition = proxyDefinition;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),new JdkInvocationHandler());
    }

    class JdkInvocationHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //pre
            if(method.getName().equals(proxyDefinition.getAopMethodName())){
                Method methodInAspect = aspectObj.getClass().getDeclaredMethod(proxyDefinition.getProxyBeforeMethod());
                methodInAspect.invoke(aspectObj);
            }

            //around如何实现？
            //执行代理方法
            Object result = method.invoke(obj, args);
            //around如何实现？

            //after
            return result;
        }
    }
}

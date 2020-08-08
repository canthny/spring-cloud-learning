package tanghao.learning.test.java.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description： jdk动态代理
 * Created By tanghao on 2020/8/8
 */
public class JDKProxy {

    private Object obj;

    public JDKProxy(Object obj){
        this.obj = obj;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),new JdkInvocationHandler());
    }

    class JdkInvocationHandler implements InvocationHandler{
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            //pre

            //around如何实现？
            //执行代理方法
            Object result = method.invoke(obj, args);
            //around如何实现？

            //after
            return result;
        }
    }
}

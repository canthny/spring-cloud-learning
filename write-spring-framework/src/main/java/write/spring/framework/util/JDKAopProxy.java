package write.spring.framework.util;

import org.springframework.util.StringUtils;
import write.spring.framework.annotation.Injection;
import write.spring.framework.domain.AopDefinition;
import write.spring.framework.domain.AopMethodInvoker;
import write.spring.framework.util.impl.InterfaceAImpl;
import write.spring.framework.util.impl.InterfaceCImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description： jdk动态代理
 * Created By tanghao on 2020/8/8
 */
public class JDKAopProxy {

    private Object obj;

    private Object aspectObj;

    private AopDefinition aopDefinition;

    public JDKAopProxy(Object obj, Object aspectObj, AopDefinition aopDefinition){
        this.obj = obj;
        this.aspectObj = aspectObj;
        this.aopDefinition = aopDefinition;
    }

    public Object getProxyInstance(){
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),new JdkAopInvocationHandler());
    }

    class JdkAopInvocationHandler implements InvocationHandler{
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

    static class JdkAopInvocationHandlerTest implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("JdkAopInvocationHandlerTest in method A");

            return null;
        }
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class clazz = InterfaceAImpl.class;
        Object object = clazz.newInstance();
        object = Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),new JdkAopInvocationHandlerTest());
        ClassB b = new ClassB();
        for(Field field:ClassB.class.getDeclaredFields()){
            field.setAccessible(true);
            field.set(b,object);
        }
        System.out.println(b);
        b.getA().A();

        Class clazzC = InterfaceCImpl.class;
        Object objectC = clazzC.newInstance();
        objectC = Proxy.newProxyInstance(objectC.getClass().getClassLoader(),objectC.getClass().getInterfaces(),new JdkAopInvocationHandlerTest());
        for(Field field:clazzC.getDeclaredFields()){
            if(field.getName().equals("count")){
                field.setAccessible(true);
                field.set(objectC,1);
            }
//            field.setAccessible(true);
//            field.set(objectC,object);
        }
    }
}

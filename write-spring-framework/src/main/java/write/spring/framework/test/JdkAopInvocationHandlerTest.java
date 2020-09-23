package write.spring.framework.test;

import write.spring.framework.test.proxy.ClassB;
import write.spring.framework.test.proxy.InterfaceAImpl;
import write.spring.framework.test.proxy.InterfaceC;
import write.spring.framework.test.proxy.InterfaceCImpl;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Description： jdk动态代理测试
 * Created By tanghao on 2020/9/18
 */
public class JdkAopInvocationHandlerTest implements InvocationHandler {

    private Object target;

    public JdkAopInvocationHandlerTest(Object target){
        this.target = target;
    }

    Object getProxy(){
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("JdkAopInvocationHandlerTest in method ="+ method);
        return method.invoke(target,args);

    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Class clazz = InterfaceAImpl.class;
        Object object = clazz.newInstance();
        JdkAopInvocationHandlerTest proxyFactoryA = new JdkAopInvocationHandlerTest(object);
        object = proxyFactoryA.getProxy();
        ClassB b = new ClassB();
        for(Field field:ClassB.class.getDeclaredFields()){
            field.setAccessible(true);
            field.set(b,object);
        }
        System.out.println(b);
        b.getA().A();

        Class clazzC = InterfaceCImpl.class;
        InterfaceC objectC = (InterfaceC)clazzC.newInstance();
        for(Field field:clazzC.getDeclaredFields()){
            if(field.getName().equals("count")){
                field.setAccessible(true);
                field.set(objectC,1);
            }
        }
        objectC.print();
        JdkAopInvocationHandlerTest proxyFactory = new JdkAopInvocationHandlerTest(objectC);
        objectC = (InterfaceC) proxyFactory.getProxy();
        for(Field field:clazzC.getDeclaredFields()){
            if(field.getName().equals("count")){
                field.setAccessible(true);
                field.set(objectC,2);
            }
        }
        objectC.print();
    }

}

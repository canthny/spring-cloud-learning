package write.spring.framework.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import write.spring.framework.test.proxy.ClassB;
import write.spring.framework.test.proxy.InterfaceAImpl;
import write.spring.framework.test.proxy.InterfaceC;
import write.spring.framework.test.proxy.InterfaceCImpl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Description： cglib增强代理
 * Created By tanghao on 2020/9/18
 */
public class CglibProxyTest implements MethodInterceptor {

    private Object target;

    public CglibProxyTest(Object target){
        this.target = target;
    }

    Object getProxy(){
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("CglibProxyTest in method ="+ method);
        Object res = methodProxy.invokeSuper(o,objects);
        return res;
    }

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        Class clazz = InterfaceAImpl.class;
        Object object = clazz.newInstance();
        CglibProxyTest proxyFactoryA = new CglibProxyTest(object);
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
        CglibProxyTest proxyFactory = new CglibProxyTest(objectC);
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

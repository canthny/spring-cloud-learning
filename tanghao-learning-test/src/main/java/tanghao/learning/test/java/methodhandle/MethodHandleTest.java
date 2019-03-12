package tanghao.learning.test.java.methodhandle;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;
import java.util.Random;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @Author： Canthny
 * @Description： 方法委托
 * @Date： Created in 2019/3/12 23:29
 */
public class MethodHandleTest {

    static class A{
        public String println(String s){
            System.out.println("A print "+s);
            return "success a";
        }
    }

    static class B{
        public String println(String s){
            System.out.println("B print "+s);
            return "success b";
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = new Random().nextInt()%2==0?new A():new B();
        //这里若打开注释，没有对结果进行强转，会报错：expected (String)String but found (String)void
//        getMethodHandle(obj).invokeExact("find method");
        String result  = (String)getMethodHandle(obj).invokeExact("find method");
        System.out.println(result);
    }

    private static MethodHandle getMethodHandle(Object obj) throws NoSuchMethodException, IllegalAccessException {
        MethodType methodType = MethodType.methodType(String.class,String.class);
        return lookup().findVirtual(obj.getClass(),"println",methodType).bindTo(obj);
    }
}

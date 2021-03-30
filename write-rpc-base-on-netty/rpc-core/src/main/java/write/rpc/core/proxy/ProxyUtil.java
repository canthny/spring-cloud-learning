package write.rpc.core.proxy;

public class ProxyUtil {

    private static final JdkProxy jdkProxy = new JdkProxy();

    public static Object getInstance(Object target){
        if(target.getClass().getInterfaces()!=null){
            return jdkProxy.newProxyInstance(target);
        }else{
            return null;
        }
    }
}

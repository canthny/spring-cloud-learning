package write.spring.framework.domain;

/**
 * Description： 代理定义
 * Created By tanghao on 2020/8/8
 */
public class ProxyDefinition {

    private String aopClassName;

    private String aopMethodName;

    private String aspectClassName;

    private String proxyBeforeMethod;

    public ProxyDefinition(String className, String method, String aspectClassName, String proxyBeforeMethod){
        this.aopClassName = className;
        this.aopMethodName = method;
        this.aspectClassName = aspectClassName;
        this.proxyBeforeMethod = proxyBeforeMethod;
    }

    public String getAopClassName() {
        return aopClassName;
    }

    public void setAopClassName(String aopClassName) {
        this.aopClassName = aopClassName;
    }

    public String getAopMethodName() {
        return aopMethodName;
    }

    public void setAopMethodName(String aopMethodName) {
        this.aopMethodName = aopMethodName;
    }

    public String getAspectClassName() {
        return aspectClassName;
    }

    public void setAspectClassName(String aspectClassName) {
        this.aspectClassName = aspectClassName;
    }

    public String getProxyBeforeMethod() {
        return proxyBeforeMethod;
    }

    public void setProxyBeforeMethod(String proxyBeforeMethod) {
        this.proxyBeforeMethod = proxyBeforeMethod;
    }
}

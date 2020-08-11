package write.spring.framework.domain;

/**
 * Description： 代理定义
 * Created By tanghao on 2020/8/8
 */
public class AopDefinition {

    private String aopClassName;

    private String aopMethodName;

    private String aspectClassName;

    private String beforeMethod;

    private String aroundMethod;

    private String afterMethod;

    public AopDefinition(String className, String method, String aspectClassName){
        this.aopClassName = className;
        this.aopMethodName = method;
        this.aspectClassName = aspectClassName;
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

    public String getBeforeMethod() {
        return beforeMethod;
    }

    public void setBeforeMethod(String beforeMethod) {
        this.beforeMethod = beforeMethod;
    }

    public String getAroundMethod() {
        return aroundMethod;
    }

    public void setAroundMethod(String aroundMethod) {
        this.aroundMethod = aroundMethod;
    }

    public String getAfterMethod() {
        return afterMethod;
    }

    public void setAfterMethod(String afterMethod) {
        this.afterMethod = afterMethod;
    }
}

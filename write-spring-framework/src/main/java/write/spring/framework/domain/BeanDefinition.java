package write.spring.framework.domain;

/**
 * Description： bean定义
 * Created By tanghao on 2020/8/1
 */
public class BeanDefinition {

    private Class<?> clazz;

    private String name;

    private String initMethod;

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }
}

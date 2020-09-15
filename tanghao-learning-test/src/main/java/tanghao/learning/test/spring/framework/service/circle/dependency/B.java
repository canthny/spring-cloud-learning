package tanghao.learning.test.spring.framework.service.circle.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description： TODO
 * Created By tanghao on 2020/7/15
 */
@Component
public class B {

    @Autowired
    private A a;
    private String id;
    private String name;

    B(){
        id = "b";
        name = "test2";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "B{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

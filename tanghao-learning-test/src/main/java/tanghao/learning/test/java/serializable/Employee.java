package tanghao.learning.test.java.serializable;

import java.io.Serializable;

/**
 * Description： TODO
 * Created By tanghao on 2020/4/30
 */
public class Employee implements Serializable {

    Employee(String name){
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                '}';
    }
}

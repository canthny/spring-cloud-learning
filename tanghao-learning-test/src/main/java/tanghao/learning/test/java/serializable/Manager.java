package tanghao.learning.test.java.serializable;

import java.io.Serializable;

/**
 * Description： TODO
 * Created By tanghao on 2020/4/30
 */
public class Manager implements Serializable {

    private static final long serialVersionUID=-2235261370010641681L;

    private int id;

    private String name;

    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Manager(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return "Manager{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employee=" + employee +
                '}';
    }
}

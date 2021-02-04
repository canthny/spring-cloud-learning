package tanghao.learning.test.spring.framework.service;

/**
 * Description： TODO
 * Created By tanghao on 2021/1/20
 */
public class User extends UserBase{

    private String name;

    private Integer age;

    public User(String pin, String name, Integer age) {
        super.setPin(pin);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

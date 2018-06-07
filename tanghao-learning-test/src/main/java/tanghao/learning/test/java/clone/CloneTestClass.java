package tanghao.learning.test.java.clone;

/**
 * @Author： Canthny
 * @Description： Clone测试类
 * @Date： Created in 2018/5/31 16:57
 */
public class CloneTestClass implements Cloneable{

    private String name;
    private int id;

    public CloneTestClass(int id,String name){
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
        CloneTestClass obj1 = new CloneTestClass(1,"tanghao");
        CloneTestClass obj2 = null;
        try {
            obj2  = (CloneTestClass)obj1.clone();
            obj2.setId(2);
            obj2.setName("xudan");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("obj1====="+obj1);
        System.out.println("obj2====="+obj2);
    }

    @Override
    public String toString() {
        return "CloneTestClass{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}

package tanghao.learning.test.java.serializable;

import java.io.*;
import java.util.Arrays;

/**
 * Description： TODO
 * Created By tanghao on 2020/4/30
 */
public class TestSerialize {



    public static void main(String[] args) {
        Manager A = new Manager(1,"张三");
        Manager B = new Manager(2,"李四");
        Employee C = new Employee("王二麻子");
        A.setEmployee(C);
        B.setEmployee(C);
        Manager[] managers = {A,B};


        try {
            ObjectOutputStream out  = new ObjectOutputStream(new FileOutputStream("D://test-out.dat"));
            out.writeObject(managers);
            out.close();

            ObjectInputStream in  = new ObjectInputStream(new FileInputStream("D://test-out.dat"));
            Manager[] read = (Manager[])in.readObject();
            Arrays.asList(read).forEach(manager -> {System.out.println(manager);});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

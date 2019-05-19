package tanghao.learning.test.java.innerclass;

import java.lang.reflect.Field;

/**
 * @Author： Canthny
 * @Description： //TODO 那么请问：这个类是干嘛的呢？
 * @Description：
 * @Date： Created in 2018/9/12 14:28
 */
public class TestClass {

    public static void main(String[] args) {
        InnerClassConvert c = new InnerClassConvert();
        Object o = c.getBusiParam();
        Field[] fields =o.getClass().getDeclaredFields();
        String[] fieldNames=new String[fields.length];
        for(int i=0;i<fields.length;i++){
            System.out.println(fields[i].getName());
            fieldNames[i]=fields[i].getName();
        }
    }
}

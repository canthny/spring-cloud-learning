package tanghao.learning.test.java.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

/**
 * Description： java Bean内省机制
 * Created By tanghao on 2020/9/4
 */
public class JavaBeanTest {

    public static void main(String[] args) throws IntrospectionException {
        User user  = new User();
        user.setAge(1);
        user.setName("tanghao");
        BeanInfo userBean = Introspector.getBeanInfo(User.class,Object.class);


        PropertyDescriptor[] propertiesDescriptors = userBean.getPropertyDescriptors();
        for(PropertyDescriptor propertyDescriptor:propertiesDescriptors){
            System.out.println(propertyDescriptor.getPropertyType());
            System.out.println(propertyDescriptor.getReadMethod());
            System.out.println(propertyDescriptor.getWriteMethod());
            System.out.println(propertyDescriptor.getName());
            System.out.println(propertyDescriptor);
        }
    }

    static class User{
        private String name;

        private Integer age;

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
}

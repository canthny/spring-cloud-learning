package write.rpc.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDto implements Serializable {

    public UserInfoDto(Integer id, String userName, Integer age) {
        this.id = id;
        this.userName = userName;
        this.age = age;
    }

    private Integer id;

    private String userName;

    private Integer age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }

    public static void main(String[] args) {
        UserInfoDto dto = new UserInfoDto(1,"tanghao",18);
        UserInfoDto dto2 = new UserInfoDto(1,"tanghao",2);
        UserInfoDto dto3 = new UserInfoDto(1,"tanghao",4);
        UserInfoDto dto4 = new UserInfoDto(1,"tanghao",18);
        List<UserInfoDto> list = new ArrayList<>();
        List<UserInfoDto> list18 = new ArrayList<>();
        list.add(dto);list.add(dto2);list.add(dto3);list.add(dto4);
        list.stream().forEach(t->{
            if(t.age.equals(18)){
                list18.add(t);
            }
        });
        System.out.println(list18);
    }
}

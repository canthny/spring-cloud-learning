package tanghao.learning.test.java.clone;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author： Canthny
 * @Description： DO 转 DTO
 * @Date： Created in 2018/12/25 14:45
 */
public class DoConvertDtoTest {

    public static void main(String[] args) {
        UserDO userDO = new UserDO(1L,"canthny","123123");
        UserDTO userDTO = new UserDTO();
        List<UserDO> listDo = new ArrayList<UserDO>();
        listDo.add(userDO);
        BeanUtils.copyProperties(userDO,userDTO);
        System.out.println(userDTO);

        UserDTO userDTO2 = ObjectMapperUtils.map(userDO,UserDTO.class);
        System.out.println(userDTO2);

        List<UserDTO> listDto = ObjectMapperUtils.mapAll(listDo,UserDTO.class);
        System.out.println(listDto);
    }

    static class UserDO{
        public UserDO(Long id,String name,String pass){
            this.id = id;
            this.name = name;
            this.password = pass;
        }

        private Long id;

        private String name;

        private String password;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    static class UserDTO{
        private Long id;

        private String name;

        private String temp;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTemp() {
            return temp;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        @Override
        public String toString() {
            return "UserDTO{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", temp='" + temp + '\'' +
                    '}';
        }
    }
}

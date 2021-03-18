package write.rpc.api;

import java.io.Serializable;

public class UserQueryReq implements Serializable {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserQueryReq{" +
                "id=" + id +
                '}';
    }
}

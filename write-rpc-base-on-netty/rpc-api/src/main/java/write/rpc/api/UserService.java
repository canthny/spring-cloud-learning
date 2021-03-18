package write.rpc.api;

public interface UserService {

    UserInfoDto getUserByCondition(UserQueryReq req);
}

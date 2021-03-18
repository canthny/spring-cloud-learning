package write.rpc.provider;

import write.rpc.api.UserInfoDto;
import write.rpc.api.UserQueryReq;
import write.rpc.api.UserService;

public class UserServiceImpl implements UserService {

    @Override
    public UserInfoDto getUserByCondition(UserQueryReq req) {
        System.out.println("req = " + req);
        return new UserInfoDto(req.getId(), "tanghao",32);
    }
}

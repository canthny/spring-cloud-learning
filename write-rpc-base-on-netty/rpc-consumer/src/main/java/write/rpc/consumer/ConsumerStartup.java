package write.rpc.consumer;

import write.rpc.api.UserInfoDto;
import write.rpc.api.UserQueryReq;
import write.rpc.api.UserService;
import write.rpc.core.client.THRpcRemoteInvoker;

public class ConsumerStartup {

    public static void main(String[] args) {
        UserService userService = new THRpcRemoteInvoker<UserService>("userService",UserService.class).getProxy();
        UserQueryReq userQueryReq = new UserQueryReq();
        UserInfoDto userInfoDto = userService.getUserByCondition(userQueryReq);
    }
}

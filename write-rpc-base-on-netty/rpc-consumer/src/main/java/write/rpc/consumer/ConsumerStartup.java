package write.rpc.consumer;

import com.alibaba.fastjson.JSONObject;
import write.rpc.api.UserInfoDto;
import write.rpc.api.UserQueryReq;
import write.rpc.api.UserService;

public class ConsumerStartup {

    public static void main(String[] args) {
        UserService userService = new THRpcInvoker<UserService>("userService",UserService.class).getProxy();
        UserQueryReq userQueryReq = new UserQueryReq();
        UserInfoDto userInfoDto = userService.getUserByCondition(userQueryReq);
        System.out.println(JSONObject.toJSONString(userInfoDto));
    }
}

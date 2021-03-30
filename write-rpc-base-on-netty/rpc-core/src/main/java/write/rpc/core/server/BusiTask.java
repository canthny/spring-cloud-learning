package write.rpc.core.server;

import io.netty.channel.ChannelHandlerContext;
import write.rpc.core.ProtocolConstants;
import write.rpc.core.factory.BeanFactory;
import write.rpc.core.protocol.THMsgHeader;
import write.rpc.core.protocol.THProtocolMsg;
import write.rpc.core.protocol.THRpcRequest;
import write.rpc.core.protocol.THRpcResponse;
import write.rpc.core.proxy.ProxyUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BusiTask implements Runnable{

    ChannelHandlerContext channelHandlerContext;
    THProtocolMsg<THRpcRequest> thProtocolMsg;

    public BusiTask(ChannelHandlerContext channelHandlerContext, THProtocolMsg<THRpcRequest> thProtocolMsg){
        this.channelHandlerContext = channelHandlerContext;
        this.thProtocolMsg = thProtocolMsg;
    }

    @Override
    public void run() {
        THRpcRequest thRpcRequest = thProtocolMsg.getBody();
        String serviceId = thRpcRequest.getServiceId();
        Object object = BeanFactory.get(serviceId);
        if(object == null){
            System.out.println("not find service provider");
        }
        Object resObj = null;
        try {
            Method method = object.getClass().getDeclaredMethod(thRpcRequest.getMethodName(),thRpcRequest.getParameterTypes());
            resObj = method.invoke(object,thRpcRequest.getParams());
        } catch (NoSuchMethodException e) {
            System.out.println("busiTask run NoSuchMethodException:"+e);
        } catch (IllegalAccessException e) {
            System.out.println("busiTask run IllegalAccessException:"+e);
        } catch (InvocationTargetException e) {
            System.out.println("busiTask run InvocationTargetException:"+e);
        }


        THProtocolMsg<THRpcResponse> response = new THProtocolMsg<>();
        THMsgHeader header = new THMsgHeader();
        header.setMagicData(ProtocolConstants.MAGIC_DATA);
        header.setVersion(ProtocolConstants.VERSION.getBytes()[0]);
        header.setSerialization(ProtocolConstants.DEFAULT_SERIALIZATION.getBytes()[0]);
        header.setRequestType(ProtocolConstants.REQUEST_TYPE_RETURN.getBytes()[0]);
        header.setRequestId(System.currentTimeMillis());
        THRpcResponse rpcResponse = new THRpcResponse();
        rpcResponse.setObject(resObj);
        response.setBody(rpcResponse);
        channelHandlerContext.writeAndFlush(response);
    }
}

package write.rpc.consumer;

import io.netty.channel.*;
import io.netty.util.concurrent.DefaultPromise;
import write.rpc.core.ProtocolConstants;
import write.rpc.core.client.ReqFuture;
import write.rpc.core.client.RequestHolder;
import write.rpc.core.protocol.*;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

public class THRpcInvoker<T> implements InvocationHandler {

    private Class<T> mapperInterface;

    private String serviceId;

    public THRpcInvoker(String serviceId, Class<T> mapperInterface){
        this.serviceId = serviceId;
        this.mapperInterface = mapperInterface;
    }

    public T getProxy(){
        return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        THProtocolMsg<THRpcRequest> protocolMsg = new THProtocolMsg<>();
        THMsgHeader header = new THMsgHeader();
        long requestId = RequestHolder.getRequestId();
        header.setMagicData(ProtocolConstants.MAGIC_DATA);
        header.setVersion(ProtocolConstants.VERSION);
        header.setRequestId(requestId);
        header.setSerialization(ProtocolConstants.DEFAULT_SERIALIZATION);
        header.setRequestType(ProtocolConstants.REQUEST_TYPE_GET);
        header.setStatus((byte) 0x1);
        protocolMsg.setHeader(header);
        THRpcRequest request = new THRpcRequest();
        request.setServiceId(serviceId);
        request.setInterfaceName(method.getDeclaringClass().getName());
        request.setAlias(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setParameterTypes(method.getParameterTypes());
        request.setParams(args);
        protocolMsg.setBody(request);
        ReqFuture<THRpcResponse> future = new ReqFuture<>(new DefaultPromise<>(new DefaultEventLoop()), ProtocolConstants.DEFAULT_TIMEOUT);
        RequestHolder.REQ_FUTURE_MAP.put(requestId, future);
        THRpcRemoteCaller rpcConsumer = new THRpcRemoteCaller();
        rpcConsumer.sendRequest(protocolMsg);
        return future.getPromise().get(future.getTimeout(), TimeUnit.MILLISECONDS).getObject();
    }
}

package write.rpc.core.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import write.rpc.core.ProtocolConstants;
import write.rpc.core.protocol.THMsgHeader;
import write.rpc.core.protocol.THProtocolCodec;
import write.rpc.core.protocol.THProtocolMsg;
import write.rpc.core.protocol.THRpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class THRpcRemoteInvoker<T> implements InvocationHandler {

    private Class<T> mapperInterface;

    private String serviceId;

    public THRpcRemoteInvoker(String serviceId,Class<T> mapperInterface){
        this.serviceId = serviceId;
        this.mapperInterface = mapperInterface;
    }

    public T getProxy(){
        return (T)Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[]{mapperInterface},this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        SocketAddress address = new InetSocketAddress("localhost",8988);
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture future = bootstrap.group(new NioEventLoopGroup()).channel(NioSocketChannel.class).handler(new THProtocolCodec()).connect(address).sync();
        THProtocolMsg<THRpcRequest> protocolMsg = new THProtocolMsg<>();
        THMsgHeader header = new THMsgHeader();
        long requestId = System.currentTimeMillis();
        header.setMagicData(ProtocolConstants.MAGIC_DATA);
        header.setVersion(ProtocolConstants.VERSION.getBytes()[0]);
        header.setRequestId(requestId);
        header.setSerialization(ProtocolConstants.DEFAULT_SERIALIZATION.getBytes()[0]);
        header.setRequestType(ProtocolConstants.REQUEST_TYPE_GET.getBytes()[0]);
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
        future.channel().writeAndFlush(protocolMsg);
        return null;
    }
}

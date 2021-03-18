package write.rpc.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.concurrent.ThreadPoolExecutor;

public class THRpcServerHandler extends SimpleChannelInboundHandler<THProtocol<THRpcRequest>> {


    @Override
    protected void messageReceived(ChannelHandlerContext ctx, THProtocol<THRpcRequest> msg) throws Exception {

    }
}

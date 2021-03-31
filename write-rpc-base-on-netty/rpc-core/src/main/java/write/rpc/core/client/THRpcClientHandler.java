package write.rpc.core.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import write.rpc.core.protocol.THProtocolMsg;
import write.rpc.core.protocol.THRpcResponse;

public class THRpcClientHandler extends SimpleChannelInboundHandler<THProtocolMsg<THRpcResponse>> {
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, THProtocolMsg<THRpcResponse> msg) throws Exception {
        long requestId = msg.getHeader().getRequestId();
        ReqFuture<THRpcResponse> future = RequestHolder.REQ_FUTURE_MAP.remove(requestId);
        future.getPromise().setSuccess(msg.getBody());
    }
}

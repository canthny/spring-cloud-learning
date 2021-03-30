package write.rpc.core.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import write.rpc.core.protocol.THProtocolMsg;
import write.rpc.core.protocol.THRpcRequest;

import java.util.concurrent.*;

public class THRpcServerHandler extends SimpleChannelInboundHandler<THProtocolMsg<THRpcRequest>> {

    ThreadPoolExecutor pool =  new ThreadPoolExecutor(20, 200, 180, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), new RejectedExecutionHandler() {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            System.out.println("rpcServerHandler pool execute exception");
        }
    });

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, THProtocolMsg<THRpcRequest> msg) throws Exception {
        if(msg!=null && msg.getBody()!=null){
            pool.execute(new BusiTask(ctx, msg));
        }else{
            //donothing
        }
    }
}

package write.rpc.core;

import io.netty.channel.ChannelHandlerContext;

public class BusiTask implements Runnable{

    ChannelHandlerContext channelHandlerContext;
    THRpcRequest thRpcRequest;

    public BusiTask(ChannelHandlerContext channelHandlerContext,THRpcRequest thRpcRequest){
        this.channelHandlerContext = channelHandlerContext;
        this.thRpcRequest = thRpcRequest;
    }

    @Override
    public void run() {

    }
}

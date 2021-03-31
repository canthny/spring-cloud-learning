package write.rpc.consumer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import write.rpc.core.client.THRpcClientHandler;
import write.rpc.core.protocol.*;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class THRpcRemoteCaller {

    private final Bootstrap bootstrap;
    private final EventLoopGroup eventLoopGroup;

    public THRpcRemoteCaller() {
        bootstrap = new Bootstrap();
        eventLoopGroup = new NioEventLoopGroup(4);
        bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new THProtocolCodec())
                                .addLast(new THRpcClientHandler());
                    }
                });
    }

    public void sendRequest(THProtocolMsg<THRpcRequest> msg) throws Exception {
        THRpcRequest request = msg.getBody();
        Object[] params = request.getParams();
        ChannelFuture future = bootstrap.connect("localhost", 8888).sync();
        future.addListener((ChannelFutureListener) arg0 -> {
            if (future.isSuccess()) {
                System.out.println("connect rpc server {} on port {} success.");
            } else {
                System.out.println("connect rpc server {} on port {} failed.");
                future.cause().printStackTrace();
                eventLoopGroup.shutdownGracefully();
            }
        });
        future.channel().writeAndFlush(msg);
    }
}

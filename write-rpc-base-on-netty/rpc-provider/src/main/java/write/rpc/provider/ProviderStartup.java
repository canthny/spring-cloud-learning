package write.rpc.provider;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import write.rpc.core.factory.BeanFactory;
import write.rpc.core.protocol.THProtocolCodec;
import write.rpc.core.protocol.THProtocolDecoder;
import write.rpc.core.protocol.THProtocolEncoder;
import write.rpc.core.server.THRpcServerHandler;

import java.net.InetSocketAddress;

/**
 * 消费提供者
 */
public class ProviderStartup {

    private static Integer port = 8888;

    public static void main(String[] args) {
        BeanFactory.put("userService",new UserServiceImpl());
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss,worker).channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>(){
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new THProtocolCodec());
                            ch.pipeline().addLast(new THRpcServerHandler());
                        }
                    });
            ChannelFuture f = b.bind(port).sync();
            //使用sync方法进行阻塞，等待服务端链路关闭之后Main函数才退出
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }  finally {
            //优雅退出，释放线程池资源
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

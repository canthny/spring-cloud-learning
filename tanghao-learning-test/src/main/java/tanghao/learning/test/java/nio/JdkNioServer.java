package tanghao.learning.test.java.nio;

import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author： Canthny
 * @Description： jdk中的nio实现
 * @Date： Created in 2019/3/26 22:43
 */
public class JdkNioServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        SocketAddress socketAddress = new InetSocketAddress("localhost",8988);
        serverSocketChannel.socket().bind(socketAddress);
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);
        while(true){
            while(selector.select()>0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                while (it.hasNext()){
                    SelectionKey selectionKey = it.next();
                    if(selectionKey.isAcceptable()){
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ|SelectionKey.OP_WRITE);
                    }else if(selectionKey.isReadable()){
                        SocketChannel serverChannel = (SocketChannel)selectionKey.channel();
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        int length = 0;
                        while((length =serverChannel.read(byteBuffer))>0){
                            byteBuffer.flip();
                            String reqData = new String(byteBuffer.array(), 0, length);
                            System.out.println("data from client is :" + reqData);
                        }
                        byteBuffer.clear();
                        byteBuffer.put("resp to client".getBytes(StandardCharsets.UTF_8));
                        byteBuffer.flip();
                        serverChannel.write(byteBuffer);
                        byteBuffer.clear();
                        serverChannel.close();
                    }else if(selectionKey.isWritable()){
                        System.out.println();
                    }else{

                    }
                    it.remove();
                }
            }
        }
    }
}

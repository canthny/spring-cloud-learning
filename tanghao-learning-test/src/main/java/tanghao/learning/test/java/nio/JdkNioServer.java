package tanghao.learning.test.java.nio;

import org.modelmapper.internal.bytebuddy.agent.builder.AgentBuilder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Author： Canthny
 * @Description： jdk中的nio实现
 * @Date： Created in 2019/3/26 22:43
 */
public class JdkNioServer {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        InetSocketAddress address = new InetSocketAddress(9090);
        serverSocket.bind(address);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("Hi!\r\n".getBytes());
        while (true){
            try{
                selector.select();
            }catch (Exception e){

            }
            Set<SelectionKey> readKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                iterator.remove();
                try {
                    if(key.isAcceptable()){
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(selector,SelectionKey.OP_WRITE|SelectionKey.OP_READ,msg.duplicate());
                        System.out.println("Accept client from "+client);
                    }
                    if(key.isReadable()){
                        System.out.println("read from client");
                    }
                    if(key.isWritable()){
                        SocketChannel client = (SocketChannel) key.channel();
                        ByteBuffer byteBuffer2 = ByteBuffer.allocate(1000);
                        int readLenth =client.read(byteBuffer2);
                        byte[] bytes = new byte[readLenth];
                        byteBuffer2.get(bytes);
                        System.out.println(new String(bytes, "UTF-8"));
                        byteBuffer2.clear();
                        ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                        while (byteBuffer.hasRemaining()){
                            if(client.write(byteBuffer)==0){
                                break;
                            }
                        }
                        client.close();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    key.channel();
                    try {
                        key.channel().close();
                    }catch (Exception e1){

                    }
                }
            }
        }
    }
}

package tanghao.learning.test.java.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author： Canthny
 * @Description： jdk的nio客户端示例
 * @Date： Created in 2019/4/24 2:31
 */
public class JdkNioClient {

    public static void main(String[] args) throws IOException {
        SocketAddress address = new InetSocketAddress(9090);
        SocketChannel client = SocketChannel.open(address);
        ByteBuffer buffer = ByteBuffer.wrap("client demo in".getBytes());
        client.write(buffer);
        buffer.clear();
        //从服务端读取消息
        int readLenth = client.read(buffer);
        //读取模式
        buffer.flip();
        byte[] bytes = new byte[readLenth];
        buffer.get(bytes);
        System.out.println(new String(bytes, "UTF-8"));
        buffer.clear();
    }
}

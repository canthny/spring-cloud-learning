package tanghao.learning.test.java.nio;

import java.nio.CharBuffer;

/**
 * @Author： Canthny
 * @Description： //TODO 那么请问：这个类是干嘛的呢？
 * @Description：
 * @Date： Created in 2019/2/25 22:53
 */
public class CharBufferTest {



    public static void main(String[] args) {
        CharBuffer buffer = CharBuffer.allocate(12);
        buffer.put("t");
        buffer.put("a");
        buffer.put("n");
        buffer.put("g");

        System.out.println(buffer.get());

        buffer.flip();
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());


    }
}

package tanghao.learning.test.java.concurrence;

/**
 * @Author： Canthny
 * @Description： 重排序问题
 * @Date： Created in 2018/11/28 0:36
 */
public class ReorderingTest {

    private static boolean ready;

    private static int number;

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        number = 42;
        ready = true;
    }

    static class ReaderThread extends Thread{
        public void run(){
            while(!ready){
                Thread.yield();
            }
            System.out.println(number);
        }
    }
}

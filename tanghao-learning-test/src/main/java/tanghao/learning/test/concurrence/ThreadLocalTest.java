package tanghao.learning.test.concurrence;

/**
 * @Author： Canthny
 * @Description： ThreadLocal来维持线程封闭性
 * @Date： Created in 2018/11/29 22:45
 */
public class ThreadLocalTest {

    ThreadLocal<Long> count = new ThreadLocal<Long>();

    public void init(){
        this.count.set(100L);
    }

    public long getValue(){
        return count.get();
    }

    public void plus(){
        long temp = count.get();
        count.set(++temp);
    }

    public static void main(String[] args) {
        final ThreadLocalTest threadLocalTest = new ThreadLocalTest();
        threadLocalTest.init();
        System.out.println(threadLocalTest.getValue());

        Thread t = new Thread(){
            public void run(){
                threadLocalTest.init();
                long temp = threadLocalTest.getValue();
                threadLocalTest.plus();
                System.out.println(threadLocalTest.getValue());
            };
        };

        t.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(threadLocalTest.getValue());
    }
}

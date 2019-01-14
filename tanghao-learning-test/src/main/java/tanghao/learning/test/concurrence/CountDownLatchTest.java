package tanghao.learning.test.concurrence;

import java.util.concurrent.CountDownLatch;

/**
 * @Author： Canthny
 * @Description： 等待时间计数器，灵活闭锁的实现
 * @Date： Created in 2019/1/15 0:37
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(10);

        Thread t = new Thread(){
            public void run(){
                for(int i=0;i<10;i++){
                    countDownLatch.countDown();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(countDownLatch.getCount());
                }
            }
        };
        t.start();

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

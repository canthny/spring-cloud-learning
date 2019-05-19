package tanghao.learning.test.java.concurrence;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author： Canthny
 * @Description： 等待时间计数器，灵活闭锁的实现
 * @Date： Created in 2019/1/15 0:37
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(10);
        final Random random = new Random();

        for(int i=0;i<10;i++) {
            Thread t = new Thread(){
                public void run(){
                    try {
                        int i = random.nextInt(10000);
                        System.out.println("thread:"+Thread.currentThread().getId()+" need time " +i);
                        Thread.sleep(i);
                        countDownLatch.countDown();
                        System.out.println(countDownLatch.getCount());
                        System.out.println("thread:"+Thread.currentThread().getId()+" is done");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
        }

        //主线程进入等待，等待所有10个异步线程都完成任务方可继续
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package tanghao.learning.test.concurrence;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author： Canthny
 * @Description： 异步任务测试类，用来进行一些时间较长的运算任务，可以再使用计算结果之前就启动。
 * @Date： Created in 2019/1/16 21:23
 */
public class FutureTaskTest {

    private static final FutureTask<Long> futureTask = new FutureTask<Long>(new Callable<Long>() {
        @Override
        public Long call() throws Exception {

            System.out.println("计算任务开始");
            Long count = 1000L+1000L;
            System.out.println("计算任务结束");
            return count;
        }
    });

    private static final Thread thread = new Thread(futureTask);

    public static void start(){
        thread.start();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTaskTest.start();

        Thread.sleep(3000L);

        System.out.println(futureTask.get());
    }
}

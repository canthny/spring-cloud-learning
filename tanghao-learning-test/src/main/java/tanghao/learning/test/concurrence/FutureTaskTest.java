package tanghao.learning.test.concurrence;

import java.util.concurrent.*;

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

    private static final FutureTask<Long> futureTask2 = new FutureTask<Long>(new Callable<Long>() {
        @Override
        public Long call() throws Exception {

            System.out.println("计算任务2开始");
            Thread.sleep(5000L);
            Long count = 1000L+1000L;
            System.out.println("计算任务2结束");
            return count;
        }
    });

    private static final Thread thread = new Thread(futureTask);
    private static final Thread thread2 = new Thread(futureTask2);

    public static void start(){
        thread.start();
        thread2.start();
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        FutureTaskTest.start();

        //处理其他任务等待2s
        Thread.sleep(2000L);

        //2s后获取计算结果
        System.out.println(futureTask.get());

        try {
            //这里开始2s内获取不到计算结果就直接抛出TimeoutException，因为计算需要5s，这里2s加上之前sleep的2s大约4s多一点还未计算出结果
            System.out.println(futureTask2.get(2, TimeUnit.SECONDS));
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

package tanghao.learning.test.concurrence;

/**
 * @Author： Canthny
 * @Description： volatile关键字测试
 * @Date： Created in 2018/11/28 0:21
 */
public class VolatileTest {

//    private static volatile boolean flag;
    private static boolean flag;

//    private static int count=0;

    public static void main(String[] args) throws InterruptedException {
        new TestThread().start();
        while(!flag){
//            Thread.yield();
//            count++;
        }
        System.out.println("get out of while");
//        System.out.println("count = "+count);
    }

    static class TestThread extends Thread{
        public void run(){
            try {
                Thread.sleep(2000);
                flag=true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

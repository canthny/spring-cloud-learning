package tanghao.learning.test.java.concurrence;

/**
 * @Author： Canthny
 * @Description： volatile关键字测试
 * @Date： Created in 2018/11/28 0:21
 */
public class VolatileTest {

//    private static volatile boolean flag;
    private static boolean flag;

    private static long count=0;

    public static void main(String[] args) throws InterruptedException {
        new TestThread().start();
        while(!flag){
            //TODO why？这里如果用了sleep，唤醒后貌似会重新加载主存中的变量值，不加volatile也可见
//            Thread.sleep(100);
            count++;
        }
        System.out.println("get out of while");
        System.out.println("count = "+count);
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

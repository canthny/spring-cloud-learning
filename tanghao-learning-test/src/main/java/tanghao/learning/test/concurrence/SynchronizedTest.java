package tanghao.learning.test.concurrence;

/**
 * @Author： Canthny
 * @Description： synchronized关键字测试
 * @Date： Created in 2019/2/18 21:46
 */
public class SynchronizedTest {

    public static void main(String[] args) {
        SynchronizedMethod m = new SynchronizedMethod();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    m.methodA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    m.methodB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }


    static class SynchronizedMethod{
        public synchronized void methodA() throws InterruptedException {
            System.out.println("method A in");
            Thread.sleep(3000);
            System.out.println("method A out");
        }
        public synchronized void methodB() throws InterruptedException {
            System.out.println("method B in");
            Thread.sleep(3000);
            System.out.println("method B out");
        }
    }
}

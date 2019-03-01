package tanghao.learning.test.concurrence;

import javax.swing.plaf.SliderUI;

/**
 * @Author： Canthny
 * @Description： 中断机制测试
 * @Date： Created in 2019/1/23 0:03
 */
public class InterruptTest {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    System.out.println("终于执行完了！");
                } catch (InterruptedException e) {
                    System.out.println("被外部线程中断了！");
                    e.printStackTrace();
                }
            }
        });
        t.start();
        t.interrupt();
    }
}

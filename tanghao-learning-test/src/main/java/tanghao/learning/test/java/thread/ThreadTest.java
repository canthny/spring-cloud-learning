package tanghao.learning.test.java.thread;

/**
 * Description： TODO
 * Created By tanghao on 2019/10/15
 */
public class ThreadTest {

    public static void main(String[] args) {
        StopThread thread = new StopThread();
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.stop();
        System.out.println(thread.isInterrupted());
        thread.interrupt();
        while (thread.isAlive()){
            System.out.println("线程还活着");
        }
        thread.print();
    }


    private static class StopThread extends Thread{
        private int i=0,j=0;

        @Override
        public void run(){
            try{
                synchronized (this){
                    ++i;
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
//                        Thread.currentThread().isInterrupted();
                        e.printStackTrace();
                    }
                    ++j;
                    Thread.sleep(10);
                }
            }catch (ThreadDeath e){
                System.out.println("这里需要做点什么，比如校验一下i和j是否是想要值，否则直接释放锁，可能产生中间数据");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        public void print(){
            System.out.println("i="+i+",j="+j);
        }
    }
}

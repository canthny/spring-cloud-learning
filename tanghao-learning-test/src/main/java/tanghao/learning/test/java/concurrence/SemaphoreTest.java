package tanghao.learning.test.java.concurrence;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @Author： Canthny
 * @Description： 信号量测试
 * @Date： Created in 2019/1/16 21:46
 */
public class SemaphoreTest {

    static class SemaphoreMap<String,Object>{
        private final Map<String,Object> map;
        private final Semaphore semaphore;

        public SemaphoreMap(int t){
            this.map = Collections.synchronizedMap(new HashMap<String,Object>());
            semaphore = new Semaphore(t);
        }

        public void push(String s,Object obj) throws InterruptedException {
            System.out.println("thread:"+Thread.currentThread().getId()+" is enter,apply permit...");
            //以此来保证HashMap中最大只有5个键值对，如果不释放，后续的put请求都会被阻塞。
            semaphore.acquire();
            System.out.println("thread:"+Thread.currentThread().getId()+" get a permit");
            map.put(s,obj);
            Thread.sleep(3000);
            //移除key的键值对，同时释放一个许可，允许下一个put请求进来。
            remove(s);
        }

        public void remove(String s){
            if(null!=map.remove(s)){
                semaphore.release();
                System.out.println("thread:"+Thread.currentThread().getId()+" release a permit...");
            }
        }

        public Semaphore getSemaphore() {
            return semaphore;
        }
    }

    public static void main(String[] args) {
        //相当于声明了一个最大容量为5的HashMap
        SemaphoreMap testMap = new SemaphoreMap<String,Object>(5);
        for(int i=0;i<10;i++){
            final int j = i;
            new Thread(new Runnable(){
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(1000));
                        testMap.push(String.valueOf(j), new Random().nextInt());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}

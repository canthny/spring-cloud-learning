package tanghao.learning.test.concurrence;

import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author： Canthny
 * @Description： 非线程安全的ListHelper
 * @Date： Created in 2018/12/4 22:26
 */
@NotThreadSafe
public class ListHelperNotThreadSafe<E>{

    public List<E> list = Collections.synchronizedList(new ArrayList<E>());

    public synchronized boolean addIfAbsent(E e){
        boolean isAbsent = !list.contains(e);
        //断点打在这一行，当前线程执行到这里是true
        if(isAbsent){
            list.add(e);
        }
        return isAbsent;
    }

    public synchronized void add(E e){
        list.add(e);
    }

    public static void main(String[] args) throws InterruptedException {
        final User user = new User("canthny",1L);

        final ListHelperNotThreadSafe<User> helper = new ListHelperNotThreadSafe<User>();
        new Thread(){
            public void run(){
//                helper.list = Collections.synchronizedList(new ArrayList<String>());
                helper.list.add(user);
            }
        }.start();
        helper.addIfAbsent(user);

        System.out.println(helper.list);

        /** 下面是线程安全的做法 start **/

        List<User> list2 = Collections.synchronizedList(new ArrayList<User>());
        final ListHelperThreadSafe<User> helper2 = new ListHelperThreadSafe(list2);
        new Thread(){
            public void run(){
                helper2.add(user);
            }
        }.start();
        helper2.addIfAbsent(user);

        Thread.sleep(1000L);
        helper2.systemOutPrint();
    }

    static class User{

        public User(String name,Long id){
            this.name = name;
            this.id = id;
        }

        private String name;

        private Long id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}

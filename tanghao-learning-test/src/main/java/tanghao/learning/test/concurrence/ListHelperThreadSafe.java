package tanghao.learning.test.concurrence;

import java.util.List;

/**
 * @Author： Canthny
 * @Description： 线程安全的ListHelper
 * @Date： Created in 2018/12/4 22:52
 */
public class ListHelperThreadSafe<E> {

    private final List<E> list;

    public ListHelperThreadSafe(List<E> list){
        this.list = list;
    }

    public synchronized boolean addIfAbsent(E e){
        boolean isAbsent = !list.contains(e);
        //断点打在这一行，当前线程执行到这里是true，这里注意看list里的东西
        if(isAbsent){
            list.add(e);
        }
        return isAbsent;
    }

    public synchronized void add(E e){
        list.add(e);
    }

    public void systemOutPrint(){
        System.out.println(list);
    }
}

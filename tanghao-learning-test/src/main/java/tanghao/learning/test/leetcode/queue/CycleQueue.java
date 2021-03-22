package tanghao.learning.test.leetcode.queue;


import net.jcip.annotations.NotThreadSafe;

@NotThreadSafe
public class CycleQueue {

    private final int size;

    private Integer[] data = null;

    private int head = 0;

    private int tail = 0;

    private int used = 0;

    private Object block = new Object();

    public CycleQueue(int k){
        this.size = k;
        data = new Integer[size];
    }

    public boolean enQueue(Integer value){
        if (isFull()){
            return false;
        }
        data[tail] = value;
        tail=(tail+1)%size;
        used++;
        return true;
    }

    public boolean deQueue(){
        if(isEmpty()){
            return false;
        }
        Integer res = data[head];
        head=(head+1)%size;
        used--;
        return true;
    }

    public Integer head(){
        if(isEmpty()){
            return null;
        }
        return data[head];
    }

    public Integer tail(){
        if(isEmpty()){
            return null;
        }
        return data[(tail+size-1)%size];
    }

    public boolean isFull(){
        return used==size;
    }

    public boolean isEmpty(){
        return used==0;
    }
}

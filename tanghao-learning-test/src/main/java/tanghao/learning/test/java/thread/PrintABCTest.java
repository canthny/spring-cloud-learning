package tanghao.learning.test.java.thread;

public class PrintABCTest {

    private static Integer count = 0;

    private static Object object = new Object();

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        MyThread t3 = new MyThread();
        t1.start();
        t2.start();
        t3.start();
    }

    static class MyThread extends Thread{
        @Override
        public void run(){
            for(int i=1;i<10;i++){
                synchronized (count.getClass()){
                    if(count%3==0){
                        System.out.print("A");
                    }else if(count%3==1){
                        System.out.print("B");
                    }else{
                        System.out.print("C ");
                    }
                    count++;
                }
            }
        }
    }
}

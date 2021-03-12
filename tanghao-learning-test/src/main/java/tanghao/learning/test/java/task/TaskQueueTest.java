package tanghao.learning.test.java.task;

import org.apache.tomcat.util.threads.TaskQueue;

import java.util.Timer;
import java.util.TimerTask;

public class TaskQueueTest {

    public static void main(String[] args) {
        TaskQueue taskQueue = new TaskQueue();
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task1 in");
            }
        };
        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task2 in");
            }
        };
        TimerTask task3 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task3 in");
            }
        };
        TimerTask task4 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("task4 in");
            }
        };
        Timer timer = new Timer();
        timer.schedule(task1,400000);
        timer.schedule(task2,300000);
        timer.schedule(task3,200000);
        timer.schedule(task4,100000);

    }

}

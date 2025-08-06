package step1;

import java.awt.*;
import java.util.Random;

public class Solution05 {
    public static void main(String[] args) throws Exception {
        long time = System.currentTimeMillis();
        Counter counter1 = new Counter();

        for (int i = 0; i < 1000; i++) {
            counter1.increment();
        }
        System.out.println(counter1.getCount());
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        Counter counter2 = new Counter();
        Runnable task = () -> {
            for (int i = 0; i < 500; i++) {
                counter2.increment();
            }
        };
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(counter2.getCount());
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        Counter counter3 = new Counter();
        Runnable task2 = () -> {
            for (int i = 0; i < 500; i++) {
                counter3.inc();
            }
        };
        Thread t3 = new Thread(task2);
        Thread t4 = new Thread(task2);
        t3.start();
        t4.start();
        t3.join();
        t4.join();
        System.out.println(counter3.getCount());
        System.out.println(System.currentTimeMillis() - time);
    }
}

class Counter {
    private int count = 0;

    // synchronized -> current 스냅샷 -> 꼬이는 걸 방지
    public synchronized void inc() {
        int current = count;
        try {
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count = current + 1;
    }

    public void increment() {
        int current = count; // counter -> count 멤버변수(필드0
        try {
            Thread.sleep(new Random().nextInt(10));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        count = current + 1;
    }

    public int getCount() {
        return count;
    }
}
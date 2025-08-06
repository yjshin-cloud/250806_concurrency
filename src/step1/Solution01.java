package step1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Solution01 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            System.out.println("%d회차".formatted(i + 1));
            randomWork();
        }
        Runnable task = () -> {
            randomWork();
        };
        System.out.println(System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
//        Runnable task = Solution01::randomWork;
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            // 생성된 스레드...
            System.out.println("%d회차".formatted(i + 1));
            // 스레드를 생성 후 작업을 돌아가면서 처리하게...
//            new Thread(task, "Worker-%d".formatted(i + 1)).start();
            Thread t = new Thread(task, "Worker-%d".formatted(i + 1));
            threads.add(t);
            t.start();
        }
        // 모든 스레드가 종료될 때까지 대기
        for (Thread t : threads) {
            try {
                t.join(); // 다 돌았다
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        System.out.println(System.currentTimeMillis() - startTime); // 메인 프로세스에서는 얼마 지나지 않아서 끝남
    }

    public static void randomWork() {
        System.out.println("작업 시작");
        Random random = new Random();
        // 로직
        // 프로세스 - 스레드
        try {
//            Thread.sleep(random.nextInt(1000, 5000));
            Thread.sleep(1000);
            // 현재 실행하고 있는 스레드 대기시킴
            // 1000ms ~ 4999ms
        } catch (Exception e) {
            throw new RuntimeException();
        }
        System.out.println("작업 종료");
    }
}
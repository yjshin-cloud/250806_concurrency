package step1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Solution02 {
    public static void main(String[] args) throws Exception {
        System.out.println("전체 처리 시작");
        ExecutorService pool = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            futures.add(pool.submit(() -> {
                System.out.println("비동기 작업 시작");
                try {
                    Thread.sleep(1000);
                    System.out.println("비동기 작업 종료");
                    return new Random().nextInt();
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }));
        }

//        f.get(); // get -> 이걸 처리해서 값을 받아오겠다 (blocking)
        // 이 시점까지 처리가 다 되어야...
        // 전체가 처리 됐다는게 확실화 되었을 때

        for (Future<Integer> f: futures) {
            System.out.println(f.get());
        }

        pool.shutdown();
        System.out.println("전체 처리 완료");
    }
}
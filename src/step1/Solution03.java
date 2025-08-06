package step1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class Solution03 {
    public static void main(String[] args) throws Exception {
        // Future -> 이미 있는 스레드 풀에서 하나를 빼와가지고... -> 비동기 작업을 하고
        // 상황에 따라서 다 실행되었다는 걸 감지해서 -> 스레드를 모두 닫는...

        // 알아서 스레드풀을 프로세스 만들어준 다음에...
        // async -> 비동기. -> 순차적으로 실행하지 않고...
        System.out.println("전체 처리 시작 " + System.currentTimeMillis());

//        List<Future<Integer>> futures = new ArrayList<>();
        List<Future<Void>> futures = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
//            Future<Integer> f = CompletableFuture.supplyAsync(() -> {
            Future<Void> f = CompletableFuture.supplyAsync(() -> {
                        try {
                            Thread.sleep(1000);
                            return 1;
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }).thenApply((x) -> x * 100) // map
                    .thenAccept((x) -> System.out.println(x));

            futures.add(f);
//            f.get();
        }

//        for (Future<Integer> f : futures) {
        for (Future<Void> f : futures) {
            f.get();
        }

        System.out.println("전체 처리 완료 " + System.currentTimeMillis());
    }
}
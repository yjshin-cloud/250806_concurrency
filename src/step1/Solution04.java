package step1;

import java.util.List;

public class Solution04 {
    public static void main(String[] args) {
        List<String> textList = List.of("고양이", "호랑이", "하마", "강아지");
//        textList.stream()
//                .forEach((text) -> {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println(text);
//                });
        textList.stream().parallel()
                .forEach((text) -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(text);
                });
    }
}
package step1;

import java.util.Random;

public class Solution01 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 3; i++) {
            randomWork();
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    public static void randomWork() {
        Random random = new Random();
        // 로직
    }
}
package step2;

import java.util.ArrayList;
import java.util.List;

public class FastPrompting {
    public static void main(String[] args) {
        // 주제 -> 예문 -> 영어로 바꿔주는.
        List<String> subjects = List.of("날씨", "건강", "취업", "취미", "운동");
        List<String> examples = new ArrayList<>();
        for (String subject : subjects) {
            String example = makeExample(subject);
            examples.add(example);
        }
        for (String example : examples) {
            String talk = makeTalk(example);
            System.out.println(talk);
        }
    }

    static String makeExample(String subject) {
        return "";
    }

    static String makeTalk(String example) {
        return "";
    }
}
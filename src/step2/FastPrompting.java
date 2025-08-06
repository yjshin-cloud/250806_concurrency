package step2;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class FastPrompting {
    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        // 주제 -> 예문 -> 영어로 바꿔주는.
        List<String> subjects = List.of("날씨", "건강", "취업", "취미", "운동");
//        List<String> examples = new ArrayList<>();
//        for (String subject : subjects) {
//            String example = makeExample(subject);
//            examples.add(example);
//            System.out.println(example);
//        }
//        for (String example : examples) {
//            String talk = makeTalk(example);
//            System.out.println(talk);
//        }
        subjects.stream().parallel()
                .map(x -> {
                    try {
                        return makeExample(x);
                    } catch (Exception e) {
                        return null;
                    }
                }).map(x -> {
                    try {
                        return makeTalk(x);
                    } catch (Exception e) {
                        return null;
                    }
                }).forEach(System.out::println);
        System.out.println("실행시간 = " + (System.currentTimeMillis() - startTime) + "ms");
    }

    static final HttpClient client = HttpClient.newHttpClient();
    // https://aistudio.google.com/apikey
    static final String URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";
    static final String GEMINI_API_KEY = System.getenv("GEMINI_API_KEY");
    static final String template = """
            {
                "contents": [
                  {
                    "parts": [
                      {
                        "text": "%s"
                      }
                    ]
                  }
                ]
              }
            """;

    static String callAPI(String text) throws Exception {
        if (GEMINI_API_KEY == null) {
            System.err.println("오! 환경변수가 없네요!");
            throw new RuntimeException();
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .headers("Content-Type", "application/json",
                        "X-goog-api-key", GEMINI_API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(
                        template.formatted(text)
                ))
                .build();
        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );
        String body = response.body(); // 응답을 받은 위치
        return body.split("\"text\": \"")[1] // 0, 1, 2....
                .split("}")[0]
                .replace("\\n", "")
                .replace("\"", "")
                .trim();
    }

    static String makeExample(String subject) throws Exception {
        return callAPI("%s(이)라는 주제로 한국어 실생활 문장을 꾸미는 문법(마크다운 등) 없이 평문으로 50자 이내로 작성해주고, 과정없이 문장 결과만 출력해주세요.".formatted(subject));
    }

    static String makeTalk(String example) throws Exception {
        return callAPI("%s(이)라는 한국어 문장을 영어 문장으로 바꾸는데 꾸미는 문법(마크다운 등) 없이 평문으로 50자 이내로 작성해주고, 과정없이 문장 결과만 출력해주세요.".formatted(example));
    }
}
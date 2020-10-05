package ch02.ex01;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * for ループの並列verを描きなさい。リストのセグメントごとに処理を行う別々のスレッドを多数生成し
 * 処理が終わるごとに結果を合計するようにしなさい.
 * (みなさんは単一カウンターを更新するためにスレッドを使用したくはないでしょう。なぜですか？）
 */
public class Main {
    private static final String filePath = "C:\\study\\java\\java-learning\\js8ri\\src\\ch02\\ch01\\alice.txt";

    public static void main(String[] args) throws IOException {
        String contents = new String(
                Files.readAllBytes(Paths.get(filePath)), StandardCharsets.UTF_8
        );
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

    }
}

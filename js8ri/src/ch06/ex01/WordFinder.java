package ch06.ex01;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 多数のスレッドが更新する最大長の文字列を管理するプログラムを書きなさい。
 * AtomicReferenceと適切な累積関数を使用しなさい。
 */
public class WordFinder {

    public static String findLongest(List<String> words) {
        AtomicReference<String> largest = new AtomicReference<>("");
        words.stream().parallel().forEach(
                str -> {
                    largest.accumulateAndGet(str,
                            (s1, s2) -> {
                                if (s1.length() > s2.length()) {
                                    return s1;
                                } else {
                                    return s2;
                                }
                            }
                    );
                }
        );
        return largest.get();
    }

    public static void main(String[] args) {
        List<String> inputStr = Arrays.asList("apple", "banana", "PouroumaCecropiifolia");
        String longest = WordFinder.findLongest(inputStr);
        System.out.println(longest);
    }
}

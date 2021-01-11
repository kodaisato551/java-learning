package ch08.ex05;

import javafx.util.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Function;
import java.util.stream.Collectors;

public class LongWordFinder {
    static long countWithStream(List<String> words) {
        return words.stream().filter(w -> w.length() > 12).count();
    }

    static long countWithForEach(List<String> words) {
        LongAdder count = new LongAdder();
        words.forEach(
                w -> {
                    if (w.length() > 12) {
                        count.add(1);
                    }
                }
        );
        return count.longValue();
    }

    static long countWithFor(List<String> words) {
        long count = 0;
        for (String str : words) {
            if (str.length() > 12) {
                count++;
            }
        }
        return count;
    }

    /**
     * 関数の処理時間
     *
     * @param function 実行する関数
     * @param input    関数の入力
     * @param <T>      実行する関数の引数
     * @param <R>      実行結果
     * @return 実行結果と実行時間
     */
    static <T, R> Pair<R, Long> measure(Function<T, R> function, T input) {
        long start = System.currentTimeMillis();
        R time = function.apply(input);
        long end = System.currentTimeMillis();
        return new Pair<>(time, end - start);
    }


    private static List<String> readFile(String path) throws IOException {
        String contents = Files.readAllLines(Paths.get(path)).stream().collect(Collectors.joining());
        List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));
        return words;
    }

    public static void main(String[] args) throws IOException {
        String path = "/Users/satokodai/Study/java-learning/js8ri/src/ch08/ex05/war_and_peace.txt";
        List<String> words = readFile(path);
        Pair<Long, Long> withStream =
                measure(LongWordFinder::countWithStream, words);
        Pair<Long, Long> withForEach =
                measure(LongWordFinder::countWithForEach, words);
        Pair<Long, Long> withFor =
                measure(LongWordFinder::countWithFor, words);
        System.out.println("カウント：" + withStream.getKey() + " 実行時間：" + withStream.getValue());
        System.out.println("カウント：" + withFor.getKey() + " 実行時間：" + withFor.getValue());
        System.out.println("カウント：" + withForEach.getKey() + " 実行時間：" + withForEach.getValue());
    }

}

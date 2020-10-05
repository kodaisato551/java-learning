package ch02.ex03;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * ある文字数以上の長い単語のうち最初の５こだけ求める処理ににおいて５番目の
 * 長い単語がいったん見つかったらfilterメソッドが呼び出されないことを検証しなさい。
 * 単純に各メソッドの呼び出しでログを出せばよいです。
 */
public class NotCalledFilterVerifier {

    /**
     * 引数th以上の文字数の単語をリスト形式で取得する。
     */
    public static List<String> getLongWordListWithStream(List<String> strings, int th) {
        AtomicInteger count = new AtomicInteger();
        List<String> longWords =
                strings.stream().filter(
                        (w) -> {
                            count.getAndIncrement();
                            System.out.println("called num: " + count);
                            return w.length() > th;
                        }
                ).limit(5).collect(Collectors.toList());
        return longWords;
    }

    public static List<String> getLongWordListWithParallelStream(List<String> strings, int th) {
        AtomicInteger count = new AtomicInteger();
        List<String> longWords =
                strings.parallelStream().filter(
                        (w) -> {
                            count.getAndIncrement();
                            System.out.println("called num: " + count);
                            return w.length() > th;
                        }
                ).limit(5).collect(Collectors.toList());
        return longWords;
    }


    public static void main(String[] args) {
        List<String> strings =
                Arrays.asList("Iron Man", "Black Widow", "Mighty Thor",
                        "Hulk", "Captain America", "Black Panther", "Wasp", "Spider Man");

        long startTime = System.nanoTime();
        NotCalledFilterVerifier.getLongWordListWithStream(strings, 5);
        long endTime = System.nanoTime();
        System.out.println("stream calc time : " + (endTime - startTime));

        startTime = System.nanoTime();
        NotCalledFilterVerifier.getLongWordListWithParallelStream(strings, 5);
        endTime = System.nanoTime();
        System.out.println("parallel calc time : " + (endTime - startTime));

    }
}

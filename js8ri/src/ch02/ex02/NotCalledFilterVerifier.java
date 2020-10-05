package ch02.ex02;

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


    public static void main(String[] args) {
        List<String> strings =
                Arrays.asList("Iron Man", "Black Widow", "Mighty Thor",
                        "Hulk", "Captain America", "Black Panther", "Wasp", "Spider Man");
        AtomicInteger count = new AtomicInteger();
        List<String> longWords =
                strings.stream().filter(
                        (w) -> {
                            count.getAndIncrement();
                            System.out.println("called num: " + count);
                            return w.length() > 5;
                        }
                ).limit(5).collect(Collectors.toList());

        longWords.forEach(System.out::println);
    }
}

package ch02.ex06;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * characterStreamメソッドは最初にArrayListを埋めて、それからそのリストをストリーム
 * に変換するという具合に多少ぎこちないです。代わりにストリームを使用して１行で書きなさい
 * 適切な方法は0からs.length()-1までの整数ストリームを作製してそれをs::charAtのメソッド参照でマップすることです。
 */
public class Main {

    public static Stream<Character> characterStream(String s) {
        return IntStream.range(0, s.length()).mapToObj(s::charAt);
    }

    public static void main(String[] args) {
        characterStream("abracatabra").forEach(System.out::println);
    }
}

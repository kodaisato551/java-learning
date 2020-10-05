package ch02.ex05;

import java.util.stream.Stream;

/**
 * 乱数生成
 */
public class RandomGenerator {
    /**
     * 乱数の無限のStreamを生成する。
     * 線形合成生成器
     */
    private static Stream<Long> generate(long a, long c, long m, long seed) {
        return Stream.iterate(seed, x -> (a * x + c) % m);
    }

    public static void main(String[] args) {
        //TODO
    }
}

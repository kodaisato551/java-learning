package ch02.ex04;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 1,4,9,16
 */
public class Main {
    public static void main(String[] args) {
        int[] values = {1, 4, 9, 16};
        Stream.of(values).forEach(e -> {
            System.out.println(e.getClass().getName());
        });
        //intの配列でした

        //intのストリームはどのように手に入る
        Arrays.stream(values).forEach(e -> {
                    System.out.println();
                }
        );
    }
}

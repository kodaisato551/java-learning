package ch08.ex07;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * nullFirst(naturalorder()).revered()を使わない
 */
public class ComparatorUtil {
    static <T extends Comparable<? super T>> Comparator<T> reversedAndNullsLast() {
        return Comparator.nullsLast(Comparator.reverseOrder());
    }

    public static void main(String[] args) {
        List<String> strings = Arrays.asList(null, "A", "B", "C", null);
        Collections.sort(strings, reversedAndNullsLast());
        strings.forEach(System.out::println);
    }
}

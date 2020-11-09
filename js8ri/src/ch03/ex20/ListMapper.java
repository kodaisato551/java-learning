package ch03.ex20;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * static メソッド で ある < T, U > List < U > map( List < T >, Function < T, U >) を 提供 し なさい。
 */
public class ListMapper {
    /**
     * convert T of list to U of list with conversion function is Function f.
     *
     * @param list
     * @param f
     * @param <T>
     * @param <U>
     * @return
     */
    static <T, U> List<U> map(List<T> list, Function<T, U> f) {
        List<U> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }
}

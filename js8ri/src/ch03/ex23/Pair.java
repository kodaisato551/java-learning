package ch03.ex23;

import java.util.function.Function;

/**
 * T 型 の 対 と なる 2 つ の オブジェクト を 表す Pair < T > クラス に対する map 操作 を 定義 し なさい。
 *
 * @param <T>
 */
public class Pair<T> {
    private final T type1;
    private final T type2;

    private Pair(T type1, T type2) {
        this.type1 = type1;
        this.type2 = type2;
    }

    public <U> Pair<U> map(Function<T, U> function) {
        return new Pair<>(function.apply(type1), function.apply(type2));
    }
}

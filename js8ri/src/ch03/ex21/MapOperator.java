package ch03.ex21;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

/**
 * static メソッド で ある < T, U > Future < U > map( Future < T >, Function < T, U >) を 提供 し なさい。
 * Future インタフェース の すべて の メソッド を 実装 し た 無名 クラス の オブジェクト を 返し なさい。 get メソッド で、 関数 を 呼び出し なさい。
 */
public class MapOperator {
    /**
     * 変換する関数を用いて、Tの要素を持つFutureをUのFutureに変換する
     *
     * @param future
     * @param <T>
     * @param <U>
     * @return
     */
    static <T, U> Future<U> map(Future<T> future, Function<T, U> function) {
        return new Future<U>() {
            @Override
            public boolean cancel(boolean b) {
                return false;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public U get() throws InterruptedException, ExecutionException {
                return function.apply(future.get());
            }

            @Override
            public U get(long l, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                return null;
            }
        };
    }
}

package ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * 3. 8 節「 例外 の 取り扱い」 の doInOrderAsync を 実装 し、 2 つ 目 の パラメータ は BiConsumer < T, Throwable > と し なさい。
 * うまい ユース ケース を 示し なさい。 3 つ 目 の パラメータ は 必要 です か。
 */
public class Main {
    public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second, Consumer<Throwable> handler) {
        Thread t = new Thread() {
            T type = null;
            Throwable throwable = null;

            public void run() {
                try {
                    type = first.get();
                } catch (Throwable t) {
                    throwable = t;
                }
                try {
                    second.accept(type, throwable);
                } catch (Throwable t) {
                    handler.accept(t);
                }
            }
        };
        t.start();
    }
}

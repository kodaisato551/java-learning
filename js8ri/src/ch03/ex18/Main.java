package ch03.ex18;

import java.util.function.Function;

/**
 * 3. 8 節「 例外 の 取り扱い」 の unchecked メソッド を 次 の 内容 に従って 実装 し なさい。
 * 具体的 には、 チェック さ れる 例外 を スロー する ラムダ 式 から Function < T, U > を 生成 する よう に し なさい。
 * 任意 の 例外 を スロー する 抽象 メソッド を 持つ 関数 型 インタフェース を 見つける か、 作成 する 必要 が ある こと に 注意 し なさい。
 * <p>
 */
public class Main {

    interface ThrowableFunction<T, R> {
        R apply(T arg) throws Exception;
    }

    public static <T, R> Function<T, R> uncheckedWithFunc(ThrowableFunction<T, R> f) {
        return (T arg) -> {
            try {
                return f.apply(arg);
            } catch (Exception e) {
                throw new RuntimeException(e);
            } catch (Throwable t) {
                throw t;
            }
        };
    }
}
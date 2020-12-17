package ch06.ex11;

import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * この メソッド は、 until 関数 が 受け入れる 値 を 生成 する まで、 action を 非同期 に 繰り返し ます。 until 関数 も 非同期 に 実行 さ れる べき です。
 * コンソール から java. net. PasswordAuthentication を 読み込む 関数、 および、 1 秒間 スリープ する こと で 正当性 検査 を シミュレート し、
 * パスワード が" secret" で ある かを 検査 する 関数 を 用い て テスト し なさい。 ヒント： 再帰 を 使用 し ます。
 */
public class Test {

    static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
        return CompletableFuture.supplyAsync(action)
                .thenComposeAsync(t -> until.test(t) ? CompletableFuture.completedFuture(t) : repeat(action, until));
    }
}

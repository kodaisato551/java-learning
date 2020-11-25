package ch05.ex03;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjuster;
import java.util.function.Predicate;

/**
 * Predicate < LocalDate > を 受け取り、 TemporalAdjuster を 返す next メソッド を 実装 し なさい。
 * 返さ れ た TemporalAdjuster は、 next メソッド に 渡さ れ た 述語 を 満足 する 翌日 の 日付を生成します。
 * EX)
 * today. with( next( w -> w. getDayOfWeek(). getValue() < 6))
 * <p>
 * この コード は、 今日 より 後 に あっ て 平日 と なる 最初 の 日付 を 返し ます。
 */
public class Adjuster {

    public TemporalAdjuster next(Predicate<LocalDate> predicate) {
        return (w) -> {
            LocalDate current = (LocalDate) w;
            do {
                current = current.plusDays(1);
            } while (!predicate.test(current));
            return current;
        };
    }
}

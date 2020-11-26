package ch05.ex04;

import java.time.LocalDate;
import java.util.function.Consumer;

/**
 * ある 月 の カレンダー を 表示 する Unix の cal プログラム と 同じ プログラム を 書き なさい。
 * たとえば、 java Cal 3 2013 を 実行 する と、 次 の よう に 表示 し ます。
 * 3 月 1 日 が 金曜日 で ある こと を 示し て い ます（ 土曜日 と 日曜 が 右端 に 表示 さ れる よう に し なさい）。
 */
public class Calender {
    private int month;
    private int year;

    public Calender(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public void show() {
        LocalDate date = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = date.plusMonths(1).minusDays(1);//月末
        date.datesUntil(endOfMonth).forEach(cal());
    }

    /**
     * 右端を土日にそろえるConsumerの生成
     *
     * @return
     */
    private static Consumer<LocalDate> cal() {
        return w -> {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < w.getDayOfWeek().getValue(); i++) {
                sb.append(" ");
            }
            sb.append(w.getDayOfMonth());
            if (w.getDayOfWeek().getValue() == 7) {
                sb.append("\n");
            }
            System.out.println(sb);
        };
    }

}

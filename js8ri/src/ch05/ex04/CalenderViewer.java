package ch05.ex04;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * ある 月 の カレンダー を 表示 する Unix の cal プログラム と 同じ プログラム を 書き なさい。
 * たとえば、 java Cal 3 2013 を 実行 する と、 次 の よう に 表示 し ます。
 * 3 月 1 日 が 金曜日 で ある こと を 示し て い ます（ 土曜日 と 日曜 が 右端 に 表示 さ れる よう に し なさい）。
 */
public class CalenderViewer {
    private int month;
    private int year;

    public CalenderViewer(int month, int year) {
        this.month = month;
        this.year = year;
    }

    public void show() {
        LocalDate date = LocalDate.of(year, month, 1);
        LocalDate endOfMonth = date.plusMonths(1).minusDays(1);//月末
        until(date, endOfMonth).forEach(cal());
    }

    public Stream<LocalDate> until(LocalDate startDate, LocalDate endDate) {
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        return IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i));
    }

    /**
     * 右端を土日にそろえるConsumerの生成
     * @return
     */
    private static Consumer<LocalDate> cal() {
        return w -> {
            if (w.getDayOfMonth() == 1) {
                String format = "%" + w.getDayOfWeek().getValue() * 3 + "d";
                System.out.printf(format, w.getDayOfMonth());
            } else {
                System.out.printf("%3d", w.getDayOfMonth());

            }
            if (w.getDayOfWeek().getValue() == 7) {
                System.out.print("\n");
            }
        };
    }


    public static void main(String[] args) {
        CalenderViewer viewer = new CalenderViewer(3, 2013);
        viewer.show();
    }

}

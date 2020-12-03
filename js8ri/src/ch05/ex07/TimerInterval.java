package ch05.ex07;

import java.time.LocalDate;

/**
 * （指定された日付の午前10時から午前11時といった）カレンダーイベントに適した、時刻のインターバルを表すTimeIntervalクラスを実装しなさい。2つのインターバルが重なっているかを検査するメソッドを提供しなさい。
 */
public class TimerInterval {
    private LocalDate from;
    private LocalDate to;

    public TimerInterval(LocalDate from, LocalDate to) {
        this.from = from;
        this.to = to;
    }

    public TimerInterval() {
        this(null, null);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public void setFrom(LocalDate from) {
        this.from = from;
    }

    public void setTo(LocalDate to) {
        this.to = to;
    }

    /**
     * http://koseki.hatenablog.com/entry/20111021/range
     *
     * @param other
     * @return
     */
    boolean doubleBooked(TimerInterval other) {
        return from.compareTo(other.to) <= 0 && other.from.compareTo(to) <= 0;
    }
}

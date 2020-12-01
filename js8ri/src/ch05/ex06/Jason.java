package ch05.ex06;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * 20世紀のすべての13日の金曜日を列挙しなさい。
 */
public class Jason {
    /**
     * 13日の金曜を表示する
     */
    static void showBadFriDay(int century) {
        int year = (century - 1) * 100 + 1;
        int endYear = century * 100;
        LocalDate date = LocalDate.of(year, 1, 13);
        do {
            if (date.getDayOfWeek().equals(DayOfWeek.FRIDAY)) {
                System.out.println(date);
            }
            date = date.plusMonths(1);
        } while (date.getYear() <= endYear);
    }


    public static void main(String[] args) {
        showBadFriDay(20);
    }
}

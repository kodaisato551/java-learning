package ch05.ex06;

import java.time.LocalDate;

/**
 * 20世紀のすべての13日の金曜日を列挙しなさい。
 */
public class Jason {
    /**
     * 13日の金曜を表示する
     */
    void showBadFriDay(int century) {
        int year = century * 100 + 1;
        LocalDate date = LocalDate.of(year, 1, 13);

    }
}

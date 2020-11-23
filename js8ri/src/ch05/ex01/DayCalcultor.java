package ch05.ex01;

import java.time.LocalDate;

/**
 * * plusDays を 使用 し ない で、 プログラマー の 日 を 計算 し なさい。
 * >minusDaysを使用する？？、うるう年で条件分岐するべき、テストも書こう
 */
public class DayCalcultor {
    public static void main(String[] args) {
        LocalDate progDays = LocalDate.of(2020, 12, 31).minusDays(365 - 255);
    }
}

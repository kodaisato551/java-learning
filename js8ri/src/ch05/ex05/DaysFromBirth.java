package ch05.ex05;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 今 までに、 あなた が 生き て き た 日数 を 表示 する プログラム を 書き なさい。
 */
public class DaysFromBirth {
    private LocalDate birthDay;

    public DaysFromBirth(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public long days() {
        LocalDate currentDate = LocalDate.now();
        return ChronoUnit.DAYS.between(birthDay, currentDate);
    }
}

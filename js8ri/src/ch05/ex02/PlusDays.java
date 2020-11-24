package ch05.ex02;

import java.time.LocalDate;

/**
 * LocalDate. of( 2000, 2, 29) に 1 年 を 加算 する と 何 が 起き ます か。 4 年 を 加算 する と どう です か。 さらに、 1 年 を 4 回 加算 する と どう なり ます か。
 *
 * 実行結果＞
 * 2001-02-28
 * 2004-02-29
 * 2004-02-28
 */
public class PlusDays {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2000,2,29);
        System.out.println(localDate.plusYears(1));
        System.out.println(localDate.plusYears(4));

        LocalDate tmp = localDate;
        for (int i=0;i<4;i++){
            tmp = tmp.plusYears(1);
        }
        System.out.println(tmp);
    }
}

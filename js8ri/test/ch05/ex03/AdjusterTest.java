package ch05.ex03;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class AdjusterTest {

    private Adjuster target;

    @Before
    public void setup() {
        target = new Adjuster();
    }

    @Test
    public void test_next() {
        LocalDate date = LocalDate.of(2020, 11, 27);//金曜日
        LocalDate nextDate = date.with(target.next(w -> w.getDayOfWeek().getValue() < 6));//１～５の平日
        LocalDate expected = LocalDate.of(2020, 11, 30);
        Assert.assertEquals(0, nextDate.compareTo(expected));
    }
}
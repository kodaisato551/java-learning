package ch05.ex07;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class TimerIntervalTest extends TestCase {
    private TimerInterval target;

    @Before
    public void setup() {
        target = new TimerInterval();
    }

    @Test
    public void test1() {
        LocalDate from = LocalDate.of(1993, 8, 27);
        LocalDate to = LocalDate.now();
        TimerInterval fromBirth = new TimerInterval(from, to);

        LocalDate from2 = LocalDate.of(1943, 1, 19);
        LocalDate to2 = LocalDate.of(2020, 10, 12);
        TimerInterval birthToPass = new TimerInterval(from2, to2);
        assertTrue(fromBirth.doubleBooked(birthToPass));
    }

    @Test
    public void test2() {
        LocalDate from = LocalDate.of(1993, 8, 27);
        LocalDate to = LocalDate.now();
        TimerInterval fromBirth = new TimerInterval(from, to);

        LocalDate from2 = LocalDate.of(1943, 1, 19);
        LocalDate to2 = LocalDate.of(1990, 10, 12);
        TimerInterval birthToPass = new TimerInterval(from2, to2);
        assertFalse(fromBirth.doubleBooked(birthToPass));
    }

}
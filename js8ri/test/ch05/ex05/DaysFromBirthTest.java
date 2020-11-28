package ch05.ex05;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class DaysFromBirthTest {

    private DaysFromBirth target;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void days() {
        target = new DaysFromBirth(LocalDate.of(2020, 10, 27));
        Assert.assertEquals(31, target.days());
    }
}
package ch08.ex01;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UnsignedIntTest {
    UnsignedInt target;

    @Before
    public void setup() {
    }

    @Test
    public void testAdd() {
        target = new UnsignedInt(1);
        assertEquals(2, target.add(1));
    }

    @Test
    public void testMinus() {
        target = new UnsignedInt(2);
        assertEquals(1, target.minus(1));
    }

    @Test
    public void testDivide() {
        target = new UnsignedInt(4);
        assertEquals(2, target.divide(2));
    }

    @Test
    public void testCompare() {
        target = new UnsignedInt(1);
        assertTrue(target.compare(0) > 0);
    }
}
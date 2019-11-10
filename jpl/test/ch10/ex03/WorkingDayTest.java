package ch10.ex03;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WorkingDayTest {
	WokingDay target;

	@Before
	public void setup() {
		target = new WokingDay();
	}

	@Test
	public void test_holiday() {
		assertFalse(target.isWorkingDaySwitch(Week.SUNDAY));
		assertFalse(target.isWorkingDayIfElse(Week.SUNDAY));
	}

	@Test
	public void test_workingday() {
		assertTrue(target.isWorkingDaySwitch(Week.MONDAY));
		assertTrue(target.isWorkingDayIfElse(Week.MONDAY));
	}
}

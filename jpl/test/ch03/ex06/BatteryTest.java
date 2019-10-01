package ch03.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class BatteryTest {
	Battery target;

	@Before
	public void setup() {
		target = new Battery();
	}

	@Test
	public void testNotEmpty() {
		assertThat(target.empty(), is(false));
	}

	@Test
	public void testEmpty() {
		target.useBattery(100);
		assertThat(target.empty(), is(true));
	}
}

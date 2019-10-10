package ch04.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class GasTankTest {
	GasTank target;

	@Before
	public void setup() {
		target = new GasTank();
	}

	@Test
	public void testNotEmpty() {
		assertThat(target.empty(), is(false));
	}

	@Test
	public void testEmpty() {
		target.useGas(100);
		assertThat(target.empty(), is(true));
	}

}

package ch03.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class XTest {
	X target;

	@Before
	public void setup() {
		target = new X();
	}

	@Test
	public void testX() {
		assertThat(target.fullMask, is(0x00ff));
	}

	@Test
	public void testMask() {
		assertThat(target.mask(0x114c), is(0x004c));
	}

}

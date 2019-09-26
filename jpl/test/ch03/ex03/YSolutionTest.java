package ch03.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class YSolutionTest {
	YSolution target;

	@Before
	public void setup() {
		target = new YSolution();
	}

	@Test
	public void testGetMaskingResult() {
		assertThat(target.getMaskingResult(), is(0x00bb));

	}
}

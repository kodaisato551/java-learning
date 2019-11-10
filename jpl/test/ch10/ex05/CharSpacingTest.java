package ch10.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class CharSpacingTest {

	@Test
	public void test_atof() {
		assertThat(CharSpacing.charSpacing('a', 'f'), is("bcde"));
	}
}

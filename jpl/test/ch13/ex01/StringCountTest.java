package ch13.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class StringCountTest {

	@Test
	public void testCount() {
		assertThat(StringCount.count("hogehogehoge", "g"), is(3));
	}

	@Test
	public void testCount_NoFound() {
		assertThat(StringCount.count("hogehogehoge", "a"), is(0));
	}

}

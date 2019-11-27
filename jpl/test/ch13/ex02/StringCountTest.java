package ch13.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class StringCountTest {

	@Test
	public void testCount() {
		assertThat(StringCount.count("hogehogehog", "ge"), is(2));
	}

	@Test
	public void testCount_NoFound() {
		assertThat(StringCount.count("hogehogehoge", "gh"), is(0));
	}

}

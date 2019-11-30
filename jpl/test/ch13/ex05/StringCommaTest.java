package ch13.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class StringCommaTest {

	@Test
	public void testStringComma_1() {
		String input = "hog";
		String expected = "hog";
		String actual = StringComma.stringComma(input, 3);
		assertThat(actual, is(expected));
	}

	@Test
	public void testStringComma_2() {
		String input = "hoge";
		String expected = "h,oge";
		String actual = StringComma.stringComma(input, 3);
		assertThat(actual, is(expected));
	}

	@Test
	public void testStringComma_3() {
		String input = "hogeho";
		String expected = "hog,eho";
		String actual = StringComma.stringComma(input, 3);
		assertThat(actual, is(expected));
	}

	@Test
	public void testStringComma_4() {
		String input = "h";
		String expected = "h";
		String actual = StringComma.stringComma(input, 3);
		assertThat(actual, is(expected));
	}

	@Test
	public void testStringComma_5() {
		String input = "";
		String expected = "";
		String actual = StringComma.stringComma(input, 3);
		assertThat(actual, is(expected));
	}

	@Test(expected = NullPointerException.class)
	public void testStringComma_6() {
		StringComma.stringComma(null, 3);
	}

}

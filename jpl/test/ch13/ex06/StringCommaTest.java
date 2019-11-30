package ch13.ex06;

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

	@Test
	public void testStringComma_degit4_1() {
		String input = "hoge";
		String expected = "hoge";
		String actual = StringComma.stringComma(input, 4);
		assertThat(actual, is(expected));
	}

	@Test
	public void testStringComma_degit4_2() {
		String input = "hogeh";
		String expected = "h,ogeh";
		String actual = StringComma.stringComma(input, 4);
		assertThat(actual, is(expected));
	}

	@Test
	public void testStringComma_degit1_1() {
		String input = "hogeh";
		String expected = "h,o,g,e,h";
		String actual = StringComma.stringComma(input, 1);
		assertThat(actual, is(expected));
	}

	@Test(expected = NullPointerException.class)
	public void testStringComma_exception() {
		StringComma.stringComma(null, 3);
	}

}

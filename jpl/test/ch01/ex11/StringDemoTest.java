package ch01.ex11;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class StringDemoTest {
	private PrintStream printStream;
	private ByteArrayOutputStream byteArrayOutputStream;

	@Before
	public void setUp() {
		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutputStream);
		System.setOut(printStream);
	}

	@Test
	public void testImprovedFibonacci() {
		StringDemo.main(null);

		String actual = byteArrayOutputStream.toString();
		String expected = "Name = Sato Kodai" + System.lineSeparator();

		assertThat(actual, is(expected));

	}

}

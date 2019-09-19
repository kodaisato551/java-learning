package ch01.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class FibonacciTest {
	private PrintStream printStream;
	private ByteArrayOutputStream byteArrayOutputStream;

	@Before
	public void setUp() {
		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutputStream);
		System.setOut(printStream);
	}

	@Test
	public void testHelloWorld() {
		Fibonacci.main(null);
		String expected = "Fibonacci" + System.lineSeparator();
		String actual = byteArrayOutputStream.toString().substring(0, expected.length());
		assertThat(actual, is(expected));
	}

}

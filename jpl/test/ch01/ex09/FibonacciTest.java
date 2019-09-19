package ch01.ex09;

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
	public void bofore() {
		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutputStream);
		System.setOut(printStream);
	}

	@Test
	public void testFinobacchi() {
		Fibonacci.main(null);
		String[] actualArray = byteArrayOutputStream.toString().split(System.lineSeparator());
		int lo = 1;
		int hi = 1;
		assertThat(Integer.parseInt(actualArray[0]), is(lo));
		for (int i = 1; i < Fibonacci.MAX_INTEGER; i++) {
			assertThat(Integer.parseInt(actualArray[i]), is(hi));
			hi = lo + hi;
			lo = hi - lo;
		}
	}

}

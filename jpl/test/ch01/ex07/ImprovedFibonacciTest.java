package ch01.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ImprovedFibonacciTest {
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
		ImprovedFibonacci.main(null);
		System.out.flush();

		int lo = 1;
		int hi = 1;
		String mark;
		String[] actualArray = byteArrayOutputStream.toString().split(System.lineSeparator());
		assertThat(actualArray[0], is("1: " + lo));
		for (int i = 2; i <= ImprovedFibonacci.MAX_INTEGER; i++) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			assertThat(actualArray[i - 1], is(i + ": " + hi + mark));
			hi = lo + hi;
			lo = hi - lo;
		}

	}

	@After
	public void end() {
		System.setOut(printStream);
	}
}

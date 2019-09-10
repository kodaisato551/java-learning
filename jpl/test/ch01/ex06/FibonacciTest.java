package ch01.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class FibonacciTest {

	@Test
	public void testFibonacci() {
		assertThat(Fibonacci.FIBO, is("Fibonacci"));
	}
}

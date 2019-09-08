package ch01.ex05;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Test;

public class HelloWorldTest {
	@Test
	public void testHelloWorld() {
		assertThat(HelloWorld.HELLO_WORLD, is("Hello, world"));
	}
}

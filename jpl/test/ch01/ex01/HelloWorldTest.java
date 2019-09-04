package ch01.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class HelloWorldTest {
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
		HelloWorld.main(null);
		System.out.flush();
		String actual = byteArrayOutputStream.toString();
		String expected = "Hello,  world" + System.lineSeparator();
		assertThat(actual, is(expected));
	}

	@After
	public void end() {
		System.setOut(printStream);
	}

}
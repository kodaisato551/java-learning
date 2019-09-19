package ch01.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class HelloWorldTest {
	private PrintStream printStream;
	private ByteArrayOutputStream byteArrayOutputStream;

	@Before
	public void setUp() {
		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutputStream);//出力ストリームを作製する。実際に値やオブジェクトが出力される
		System.setOut(printStream);//標準出力としてSetする
	}

	@Test
	public void testHelloWorld() {
		HelloWorld.main(null);
		String actual = byteArrayOutputStream.toString();
		String expected = "Hello,  world" + System.lineSeparator();
		assertThat(actual, is(expected));
	}

}

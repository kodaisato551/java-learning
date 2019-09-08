package ch01.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SqureTest {
	private PrintStream printStream;
	private ByteArrayOutputStream byteArrayOutputStream;


	@Before
	public void bofore() {
		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream( byteArrayOutputStream);
		System.setOut(printStream);
		}

	@Test
	public void testSqure() {
		Squre.main(null);
		System.out.flush();
		String[] arrayStr = byteArrayOutputStream.toString().split(System.lineSeparator());
		for(int i =0;i<Squre.UPPER_LIMIT;i++) {
			assertThat(Integer.parseInt(arrayStr[i]),is(i*i));
		}
	}

	@After
	public void end() {
		System.setOut(printStream);
	}
}

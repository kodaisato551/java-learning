package ch02.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	LinkedList target;
	LinkedList next;
	Object element;

	private PrintStream printStream;
	private ByteArrayOutputStream byteArrayOutputStream;

	@Before
	public void setup() {
		element = "element";
		Object nextElement = "element2";
		target = new LinkedList(element, null);
		next = new LinkedList(nextElement, null);

		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutputStream);
		System.setOut(printStream);

	}

	@Test
	public void testGet() {
		assertThat(target.getElement(), is(element));
		assertThat(target.getNext(), is(nullValue()));
	}

	@Test
	public void testSetAndGetNext() {
		target.setNext(next);
		assertThat(target.getNext(), is(next));
	}

	/**
	 * mainを実行したときに連続に期待したVehicleのID通りに表示されるか
	 */
	@Test
	public void testMain() {
		LinkedList.main(null);
		String[] actualArray = byteArrayOutputStream.toString().split(System.lineSeparator());
		for (int i = 0; i < 2; i++) {
			assertThat(actualArray[i], is(String.valueOf(i)));
		}
	}

	@After
	public void end() {
		System.setOut(printStream);
	}

}

package ch11.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	LinkedList<String> target;
	LinkedList<String> next;
	String element;

	@Before
	public void setup() {
		element = "element";
		String nextElement = "element2";
		target = new LinkedList<String>(element, null);
		next = new LinkedList<String>(nextElement, null);

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

}

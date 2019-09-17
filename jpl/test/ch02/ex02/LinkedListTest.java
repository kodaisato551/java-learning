package ch02.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	LinkedList target;
	LinkedList next;
	Object element;

	@Before
	public void setup() {
		element = "element";
		Object nextElement = "element2";
		target = new LinkedList(element, null);
		next = new LinkedList(nextElement, null);

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

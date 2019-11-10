package ch10.ex04;

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

	@Test
	public void testToString() {
		LinkedList list = new LinkedList("v1");
		list.setNext(new LinkedList("v2"));
		String actual = list.toString();
		String expected = "[v1,v2]";
		assertThat(actual, is(expected));
	}

	@Test
	public void testAddAll() {
		LinkedList list = new LinkedList("v1");
		list.addAll("v2", "v3");
		String actual = list.toString();
		String expeted = "[v1,v2,v3]";

		assertThat(actual, is(expeted));

	}

}

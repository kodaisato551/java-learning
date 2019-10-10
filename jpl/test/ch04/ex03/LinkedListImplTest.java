package ch04.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListImplTest {
	LinkedList target;
	LinkedList next;
	Object element;

	@Before
	public void setup() {
		element = "element";
		Object nextElement = "element2";
		target = new LinkedListImpl(element, null);
		next = new LinkedListImpl(nextElement, null);
	}

	@Test
	public void testGetNext() {
		assertThat(target.getElement(), is(element));
		assertThat(target.getNext(), is(nullValue()));
	}

	@Test
	public void testSetNext() {
		target.setNext(next);
		assertThat(target.getNext(), is(next));
	}

	@Test
	public void testAdd() {
		target.add("e2");
		assertThat(target.getNext().getElement(), is("e2"));
	}

	@Test
	public void testSize() {
		target.add("e2");
		target.add("e3");

		assertThat(target.size(), is(3));
	}

}

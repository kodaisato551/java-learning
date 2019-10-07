package ch03.ex10;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * リスト内の要素数を取得するメソッドsize()のテストを追加
 */
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

	@Test
	public void testSize() {
		LinkedList list = new LinkedList("v1");
		list.addAll("v2", "v3");
		assertThat(list.size(), is(3));
	}

	/**
	 * リストに対する変更は他方のリストには影響ないが、
	 * リストが参照しているオブジェクトに対する変更は、他方のリストから見えます
	 * TODO 上のテストコードを描く
	 */
	@Test
	public void testClone() {
		LinkedList cloneLinkedList = target.clone();
	}

}

package ch01.ex15;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class ParcticeLookupTest {

	private PracticeLookup target;
	private Object value1;

	@Before
	public void setUp() {
		value1 = "value1";
		target = new PracticeLookup("key1", value1);

	}

	@Test
	public void testFind() {
		Object actual = target.find("key1");
		assertThat(actual, is(value1));
	}

	@Test
	public void testFind_notFound() {
		Object actual = target.find("key2");
		assertThat(actual, is(nullValue()));
	}

	@Test
	public void testAdd() {
		Object value2 = new Object();
		target.add("key2", value2);
		Object actual = target.find("key2");
		assertThat(actual, is(value2));
	}

	@Test
	public void testRemove() {
		target.remove("key2");
		Object actual = target.find("key2");
		assertThat(actual, is(nullValue()));
	}

}

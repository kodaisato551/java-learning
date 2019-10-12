package ch01.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class PointTest {

	Point target;

	@Before
	public void setup() {
		target = new Point();
	}

	@Test
	public void testClear() {
		target.move(10, 20);
		target.clear();
		assertThat(target.x, is(0.));
		assertThat(target.y, is(0.));
	}

	@Test
	public void testMove() {
		target.move(10, 20);
		assertThat(target.x, is(10.));
		assertThat(target.y, is(20.));
	}

	@Test
	public void testCopy() {
		Point point = new Point();
		point.move(20, 30);
		target.copy(point);

		assertThat(target.x, is(20.));
		assertThat(target.y, is(30.));
	}

}

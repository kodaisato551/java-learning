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
	public void getXAndY() {
		target.x = 10;
		target.y = 11;
		assertThat(target.getX(), is(10.));
		assertThat(target.getY(), is(11.));
	}

}

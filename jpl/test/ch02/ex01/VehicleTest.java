package ch02.ex01;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.Test;

public class VehicleTest {

	Vehicle target;

	@Before
	public void setup() {
		target = new Vehicle("a");
		target.setDirection(30.);
		target.setSpeed(50.);
	}

	@Test
	public void testGetter() {
		assertThat(target.getSpeed(), is(50.));
		assertThat(target.getDirection(), is(30.));
		assertThat(target.getOwner(), is("a"));
	}

	@Test
	public void testSetSpeed() {
		target.setSpeed(60);
		assertThat(target.getSpeed(), is(60.));
	}

	@Test
	public void testSetDirection() {
		target.setDirection(60);
		assertThat(target.getDirection(), is(60.));
	}

	@Test
	public void testSetOwner() {
		target.setOwner("b");
		assertThat(target.getOwner(), is("b"));
	}
}

package ch02.ex03;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleTest {

	private static Vehicle target;

	@BeforeClass
	public static void setup() {
		target = new Vehicle(50, 30, "a");
	}

	@Before
	public void setupForEachTest() {
		target.setDirection(30);
		target.setSpeed(50);
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

	@Test
	public void getID() {
		assertThat(target.getID(), is(0));
	}

	@Test
	public void getNextID() {
		assertThat(Vehicle.getNextID(), is(1));
	}
}

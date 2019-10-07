package ch02.ex07;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleTest {
	static Vehicle target;

	@BeforeClass
	public static void setup() {
		target = new Vehicle("a");
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
	public void testGetID() {
		Vehicle v1 = new Vehicle();
		assertThat(target.getID(), is(not(v1.getID())));
	}

	@Test
	public void testGetNextID() {
		Vehicle v1 = new Vehicle();
		assertThat(Vehicle.getNextID(), is(v1.getID() + 1));
	}

	@Test
	public void testVehicleStaus() {
		String expcted = new StringBuilder().append("Owner : " + "a" + "\n")
				.append("ID : " + 0 + "\n")
				.append("Speed : " + 50. + "\n")
				.append("Direction : " + 30. + "\n")
				.toString();
		String actual = target.getVehicleStaus();
		assertThat(actual, is(expcted));
	}

}

package ch03.ex08;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class PassengerVehicleTest {

	private static PassengerVehicle target;

	@BeforeClass
	public static void setup() {
		target = new PassengerVehicle("a", 5);
	}

	@Test
	public void testToString() {
		String actual = target.toString();
		String expected = new StringBuilder()
				.append("Owner : a\n")
				.append("ID : 0\n")
				.append("Speed : 0.0\n")
				.append("Direction : 0.0\n")
				.append("seatNum : 5\n")
				.append("passengerNum : 0\n").toString();
		assertThat(actual, is(expected));

	}

	@Test
	public void testGetPassengerNum() {
		assertThat(target.getPassengerNum(), is(0));
	}

	@Test
	public void testSetPasserngerNum() {
		target.setPassengerNum(3);
		assertThat(target.getPassengerNum(), is(3));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetPasserngerNum_exception() {
		target.setPassengerNum(10);
	}

	@Test
	public void testClone() {
		PassengerVehicle clonedVehicle = target.clone();
		assertTrue(clonedVehicle != target);
		assertTrue(clonedVehicle.getClass() == target.getClass());
		assertThat(clonedVehicle.getOwner(), is(target.getOwner()));
		assertThat(clonedVehicle.getDirection(), is(target.getDirection()));
		assertThat(clonedVehicle.getSpeed(), is(target.getSpeed()));
		assertThat(clonedVehicle.getPassengerNum(), is(target.getPassengerNum()));
		assertThat(clonedVehicle.getSeatNum(), is(target.getSeatNum()));
	}

}

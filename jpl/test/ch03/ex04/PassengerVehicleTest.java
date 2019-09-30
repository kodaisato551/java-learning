package ch03.ex04;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

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
				.append("NextID : 1\n")
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

}

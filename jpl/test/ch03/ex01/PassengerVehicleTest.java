package ch03.ex01;

import org.junit.BeforeClass;

public class PassengerVehicleTest {

	private static PassengerVehicle target;

	@BeforeClass
	public static void setup() {
		target = new PassengerVehicle("a", 5);
	}

}

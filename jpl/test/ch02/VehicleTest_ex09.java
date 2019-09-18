package ch02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch02.ex09.Vehicle;

public class VehicleTest_ex09 {
	private static Vehicle target;

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
	public void getID() {
		assertThat(target.getID(), is(0));
	}

	@Test
	public void getNextID() {
		System.out.println(1);
		assertThat(Vehicle.getNextID(), is(1));
	}

	@Test
	public void printVehicleStaus() {
		String expcted = new StringBuilder().append("Owner : " + "a" + "\n")
				.append("ID : " + 0 + "\n")
				.append("Speed : " + 50. + "\n")
				.append("Direction : " + 30. + "\n")
				.append("NextID : "+1+"\n")
				.toString();
		String actual = target.getVehicleStaus();
		assertThat(actual, is(expcted));
	}

	/**
	 * こちらが先に呼び出される関係で
	 * getNextIDが落ちる。
	 * どう対処すればよいかわかりませんでした。
	 * Junitではテストが実行される
	 */
	@Test
	public void testGetMaxID() {
		System.out.println(2);
		Vehicle latestVehicle = new Vehicle("b");
		assertThat(Vehicle.getMaxID(), is(latestVehicle.getID()));
		latestVehicle = null;
	}

}

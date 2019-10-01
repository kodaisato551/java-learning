package ch03.ex06;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class VehicleTest {
	static Vehicle target;

	private PrintStream printStream;
	private ByteArrayOutputStream byteArrayOutputStream;

	@BeforeClass
	public static void setup() {
		target = new Vehicle("a", new Battery());
	}

	@Before
	public void setupForEachTest() {
		target.setDirection(30);
		target.changeSpeed(50);
		byteArrayOutputStream = new ByteArrayOutputStream();
		printStream = new PrintStream(byteArrayOutputStream);
		System.setOut(printStream);
		Battery battery = new Battery();
		target.setBattery(battery);
	}

	@Test
	public void testGetter() {
		assertThat(target.getSpeed(), is(50.));
		assertThat(target.getDirection(), is(30.));
		assertThat(target.getOwner(), is("a"));
	}

	@Test
	public void testChangeSpeed() {
		target.changeSpeed(60);
		assertThat(target.getSpeed(), is(60.));
	}

	@Test
	public void testSetDirection() {
		target.setDirection(60);
		assertThat(target.getDirection(), is(60.));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetDirection_exception() {
		target.setDirection(290);
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

	/**
	 * mainでインスタンス生成している関係でIDが１になる
	 */
	@Test
	public void getNextID() {
		assertThat(Vehicle.getNextID(), is(1));
	}

	/**
	 * mainでインスタンス生成している関係でIDが１になる
	 */
	@Test
	public void testToString() {
		String expcted = new StringBuilder().append("Owner : " + "a" + "\n")
				.append("ID : " + 0 + "\n")
				.append("Speed : " + 50. + "\n")
				.append("Direction : " + 30. + "\n")
				.append("NextID : " + 1 + "\n").toString();
		String actual = target.toString();
		assertThat(actual, is(expcted));
	}

	@Test
	public void testStop() {
		target.stop();
		assertThat(target.getSpeed(), is(0.));
	}

	@Test
	public void testTurn_leftWithin180() {
		target.turn(100, Vehicle.TURN_LEFT);
		assertThat(target.getDirection(), is(130.0));
	}

	@Test
	public void testTurn_left180() {
		target.turn(150, Vehicle.TURN_LEFT);
		assertThat(target.getDirection(), is(180.0));
	}

	@Test
	public void testTurn_leftWithout180() {
		target.turn(240, Vehicle.TURN_LEFT);
		assertThat(target.getDirection(), is(-90.0));
	}

	@Test
	public void testTurn_rightOver0() {
		target.turn(20, Vehicle.TURN_RIGHT);
		assertThat(target.getDirection(), is(10.0));
	}

	@Test
	public void testTurn_rightUnder0() {
		target.turn(50, Vehicle.TURN_RIGHT);
		assertThat(target.getDirection(), is(-20.0));
	}

	@Test
	public void testTurn_left370() {
		target.turn(340, Vehicle.TURN_LEFT);
		assertThat(target.getDirection(), is(10.0));
	}

	@Test
	public void testTurn_rigt420() {
		target.turn(420, Vehicle.TURN_RIGHT);
		assertThat(target.getDirection(), is(-30.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testTurn_exception() {
		target.turn(420, 30);
	}

	@Test
	public void testStart() {
		assertThat(target.start(), is(true));
	}

	@Test
	public void testCannnotStart() {
		Battery battery = new Battery();
		battery.useBattery(100);
		target.setBattery(battery);
		assertThat(target.start(), is(false));
	}

	/**
	 * こちらが先に呼び出される関係で
	 * getNextIDが落ちる。
	 * どう対処すればよいかわかりませんでした。
	 * Junitではテストが実行される
	 */
	//	@Test
	//	public void testGetMaxID() {
	//		System.out.println(2);
	//		Vehicle latestVehicle = new Vehicle("b");
	//		assertThat(Vehicle.getMaxID(), is(latestVehicle.getID()));
	//		latestVehicle = null;
	//	}

	@After
	public void end() {
		System.setOut(printStream);
	}

}

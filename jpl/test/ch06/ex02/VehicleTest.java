package ch06.ex02;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ch06.ex02.Turn;
import ch06.ex02.Vehicle;

/**
 * ex09で失敗したtestGetMaxID()はコメントアウトしている
 */
public class VehicleTest {
	static Vehicle target;

	@BeforeClass
	public static void setup() {
		target = new Vehicle("a");
	}

	@Before
	public void setupForEachTest() {
		target.setDirection(30);
		target.changeSpeed(50);
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
	public void testToString() {
		String expcted = new StringBuilder().append("Owner : " + "a" + "\n")
				.append("ID : " + 0 + "\n")
				.append("Speed : " + 50. + "\n")
				.append("Direction : " + 30. + "\n")
				.toString();
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
		target.turn(100, Turn.TURN_LEFT);
		assertThat(target.getDirection(), is(130.0));
	}

	@Test
	public void testTurn_left180() {
		target.turn(150, Turn.TURN_LEFT);
		assertThat(target.getDirection(), is(180.0));
	}

	@Test
	public void testTurn_leftWithout180() {
		target.turn(240, Turn.TURN_LEFT);
		assertThat(target.getDirection(), is(-90.0));
	}

	@Test
	public void testTurn_rightOver0() {
		target.turn(20, Turn.TURN_RIGHT);
		assertThat(target.getDirection(), is(10.0));
	}

	@Test
	public void testTurn_rightUnder0() {
		target.turn(50, Turn.TURN_RIGHT);
		assertThat(target.getDirection(), is(-20.0));
	}

	@Test
	public void testTurn_left370() {
		target.turn(340, Turn.TURN_LEFT);
		assertThat(target.getDirection(), is(10.0));
	}

	@Test
	public void testTurn_rigt420() {
		target.turn(420, Turn.TURN_RIGHT);
		assertThat(target.getDirection(), is(-30.0));
	}

	@Test
	public void testGetMaxID() {
		Vehicle latestVehicle = new Vehicle("b");
		assertThat(Vehicle.getMaxID(), is(latestVehicle.getID()));
		latestVehicle = null;
	}

}

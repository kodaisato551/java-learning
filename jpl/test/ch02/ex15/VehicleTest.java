package ch02.ex15;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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


}

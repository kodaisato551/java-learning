package ch03.ex09;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GarageTest {
	Garage target;

	@Before
	public void setup() {
		target = new Garage(10);
	}

	@Test
	public void testClone() {
		Garage cloned = target.clone();
		assertFalse(cloned == target);
		assertTrue(cloned.getClass() == target.getClass());
		int size = target.getVehicleNum();
		for (int i = 0; i < size; i++) {
			isVehicleSameHelper(cloned.get(i), target.get(i));
		}
	}

	/**
	 * v1とv2のオブジェクトが等しいかのテストのHelperメソッド
	 * @param v1
	 * @param v2
	 */
	private void isVehicleSameHelper(Vehicle v1, Vehicle v2) {
		assertThat(v1.getDirection(), is(v2.getDirection()));
		assertThat(v1.getOwner(), is(v2.getOwner()));
		assertThat(v1.getSpeed(), is(v2.getSpeed()));
	}
}

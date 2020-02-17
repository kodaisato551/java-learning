package ch03.ex09;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {
    private static Vehicle target;

    @BeforeClass
    public static void setup() {
		VehicleTest.target = new Vehicle("a");
    }

    @Before
    public void setupForEachTest() {
		VehicleTest.target.setDirection(30);
		VehicleTest.target.changeSpeed(50);
    }

    @Test
    public void testGetter() {
        assertThat(VehicleTest.target.getSpeed(), is(50.));
        assertThat(VehicleTest.target.getDirection(), is(30.));
        assertThat(VehicleTest.target.getOwner(), is("a"));
    }

    @Test
    public void testChangeSpeed() {
		VehicleTest.target.changeSpeed(60);
        assertThat(VehicleTest.target.getSpeed(), is(60.));
    }

    @Test
    public void testSetDirection() {
		VehicleTest.target.setDirection(60);
        assertThat(VehicleTest.target.getDirection(), is(60.));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDirection_exception() {
		VehicleTest.target.setDirection(290);
    }

    @Test
    public void testSetOwner() {
		VehicleTest.target.setOwner("b");
        assertThat(VehicleTest.target.getOwner(), is("b"));
    }

    @Test
    public void testGetID() {
        Vehicle v1 = new Vehicle();
        assertThat(VehicleTest.target.getID(), is(not(v1.getID())));
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
        String actual = VehicleTest.target.toString();
        assertThat(actual, is(expcted));
    }

    @Test
    public void testStop() {
		VehicleTest.target.stop();
        assertThat(VehicleTest.target.getSpeed(), is(0.));
    }

    @Test
    public void testTurn_leftWithin180() {
		VehicleTest.target.turn(100, Vehicle.TURN_LEFT);
        assertThat(VehicleTest.target.getDirection(), is(130.0));
    }

    @Test
    public void testTurn_left180() {
		VehicleTest.target.turn(150, Vehicle.TURN_LEFT);
        assertThat(VehicleTest.target.getDirection(), is(180.0));
    }

    @Test
    public void testTurn_leftWithout180() {
		VehicleTest.target.turn(240, Vehicle.TURN_LEFT);
        assertThat(VehicleTest.target.getDirection(), is(-90.0));
    }

    @Test
    public void testTurn_rightOver0() {
		VehicleTest.target.turn(20, Vehicle.TURN_RIGHT);
        assertThat(VehicleTest.target.getDirection(), is(10.0));
    }

    @Test
    public void testTurn_rightUnder0() {
		VehicleTest.target.turn(50, Vehicle.TURN_RIGHT);
        assertThat(VehicleTest.target.getDirection(), is(-20.0));
    }

    @Test
    public void testTurn_left370() {
		VehicleTest.target.turn(340, Vehicle.TURN_LEFT);
        assertThat(VehicleTest.target.getDirection(), is(10.0));
    }

    @Test
    public void testTurn_rigt420() {
		VehicleTest.target.turn(420, Vehicle.TURN_RIGHT);
        assertThat(VehicleTest.target.getDirection(), is(-30.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTurn_exception() {
		VehicleTest.target.turn(420, 30);
    }

    @Test
    public void testGetMaxID() {
        Vehicle latestVehicle = new Vehicle("b");
        assertThat(Vehicle.getMaxID(), is(latestVehicle.getID()));
        latestVehicle = null;
    }

    /**
     * IDが異なるのが正常
     */
    @Test
    public void testClone() {
        Vehicle clonedVehicle = VehicleTest.target.clone();
        assertTrue(clonedVehicle != VehicleTest.target);
        assertTrue(clonedVehicle.getClass() == VehicleTest.target.getClass());
        assertThat(clonedVehicle.getOwner(), is(VehicleTest.target.getOwner()));
        assertThat(clonedVehicle.getDirection(), is(VehicleTest.target.getDirection()));
        assertThat(clonedVehicle.getSpeed(), is(VehicleTest.target.getSpeed()));
    }


}

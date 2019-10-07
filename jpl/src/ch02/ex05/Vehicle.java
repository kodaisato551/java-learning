package ch02.ex05;

/**
 * Vehicleにmainを描いて数個の乗り物作成そのフィールド値を生成
 * @author Sato Kodai
 *
 */
public class Vehicle {

	private double speed;
	private double direction;
	private String owner;

	private static int nextID;

	private final int ID;

	public Vehicle() {
		this("");
	}

	public Vehicle(String owner) {
		this.owner = owner;
		ID = nextID++;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public static int getNextID() {
		return nextID;
	}

	public int getID() {
		return ID;
	}

	public static void main(String[] args) {
		Vehicle v1 = new Vehicle("a");
		v1.setDirection(10);
		v1.setSpeed(20);
		System.out.println(v1.getVehicleStaus());
		Vehicle v2 = new Vehicle("b");
		v2.setDirection(30);
		v2.setSpeed(40);
		System.out.println(v2.getVehicleStaus());
	}

	public String getVehicleStaus() {
		StringBuilder str = new StringBuilder();
		str.append("Owner : " + this.owner + "\n")
				.append("ID : " + this.ID + "\n")
				.append("Speed : " + this.speed + "\n")
				.append("Direction : " + this.direction + "\n");
		return str.toString();
	}

}

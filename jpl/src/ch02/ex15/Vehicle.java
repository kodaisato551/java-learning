package ch02.ex15;

/**
 *
 *setSpeed > changeSpeedに名称変更
 *stopの実装（speedをゼロにする）
 *
 */
public class Vehicle {

	private double speed;
	private double direction;
	private String owner;

	private static int nextID = 0;

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

	public void changeSpeed(double speed) {
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
		System.out.println(v1.toString());

		Vehicle v2 = new Vehicle("b");
		System.out.println(v2.toString());
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Owner : " + this.owner + "\n")
				.append("ID : " + this.ID + "\n")
				.append("Speed : " + this.speed + "\n")
				.append("Direction : " + this.direction + "\n");
		return str.toString();
	}

	public static int getMaxID() {
		return nextID - 1;
	}

	public void stop() {
		this.speed = 0;
	}

}

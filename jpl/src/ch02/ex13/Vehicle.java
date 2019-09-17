package ch02.ex13;

public class Vehicle {

	/**
	 * privateにすべき条件
	 * ・このクラス外からアクセスされたくない値
	 * 　speed,direction,ID,nextID,ownerなどは変更されるべきでない
	 *
	 */
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
		ID = nextID;
		nextID++;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double mSpeed) {
		this.speed = mSpeed;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double mDirection) {
		this.direction = mDirection;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String mOwner) {
		this.owner = mOwner;
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
				.append("Direction : " + this.direction + "\n")
				.append("NextID : " + Vehicle.nextID + "\n");
		return str.toString();
	}

	public static int getMaxID() {
		return nextID - 1;
	}

}

package ch02.ex04;

public class Vehicle {

	private double speed;
	private double direction;
	private String owner;

	private static int nextID;

	/**
	 * final にすべきかの
	 * ３つの条件
	 * ・objectの不変な属性を表しているか
	 * 　＞YES
	 * ・オブジェクトが生成されたときに決まっているか
	 * 　＞オブジェクト生成時にIDがないのはおかしいのでYES
	 * ・オブジェクト生成時にそのフィールドを設定するのは、常に実用的で適切か
	 *　 ＞オブジェクト生成時にIDがないのはおかしいのでYES
	 *
	 */
	private final int ID;

	public Vehicle(double speed, double direction, String owner) {
		this.speed = speed;
		this.direction = direction;
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

}

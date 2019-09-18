package ch02.ex13;

public class Vehicle {

	/**
	 * ex10時点と同様のソースコード
	 *
	 * ID,nextIDなどの値は外部から修正されるべきではないのでsetterは提供するべきではない。
	 * speedやdirectionは乗り物の速度、方向など動的に変更するのでsetterを提供するべき。
	 * ownerも持ち主が変更する場合があるためsetterを用意しておくが、一度インスタンスが生成されて二度と持ち主が変わらないのであれば不要。
	 * 全てのフィールドにgetterを用意する。しかしこのクラス内のみで使うと想定されたフィールドにはgetterは不要。
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

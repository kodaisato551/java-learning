package ch03.ex09;

/**
 *
 *・車を回転する角度と回転方向受け取りdirectionを変更するtrunメソッドの実装
 *・セットできる角度に上限と下限を設ける。その範囲を外れた場合IllegalArgumetExceptionをはく
 */
public class Vehicle implements Cloneable {

	private double speed;
	private double direction;
	private String owner;

	private static int nextID = 0;

	private final int ID;

	public static final int TURN_LEFT = 0;
	public static final int TURN_RIGHT = 1;
	private static final double DIGREE_MIN = -180.0;
	private static final double DIGREE_MAX = 180.0;

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

	public void setDirection(double direction) throws IllegalArgumentException {
		if (direction > DIGREE_MIN && direction <= DIGREE_MAX) {
			this.direction = direction;
		} else {
			throw new IllegalArgumentException("argument must be (-pi,pi]");
		}
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

		v2.turn(50, TURN_LEFT);
		System.out.println(v2.getDirection());
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

	/**
	 *	 マイナスで入力した場合、絶対値に変換されます。
	 *  360度以上の値が入力された場合３６０の剰余をとります
	 * @param rotationAngle
	 * @param rotationMode
	 * @throws IllegalArgumentException
	 */
	public void turn(double rotationAngle, int rotationMode) throws IllegalArgumentException {
		rotationAngle = Math.abs(rotationAngle) % 360;
		double direction = this.direction;
		if (rotationMode == TURN_LEFT) {
			direction += rotationAngle;
			if (direction > 180.0) {
				direction -= 360.0;
			}
			this.direction = direction;
		} else if (rotationMode == TURN_RIGHT) {
			direction -= rotationAngle;
			if (direction < -180) {
				direction += 360.0;
			}
			this.direction = direction;
		} else {
			throw new IllegalArgumentException("mode must be 0 or 1");
		}
	}

	/**
	 * コピーコンストラクタ
	 * @param original
	 */
	protected Vehicle(Vehicle original) {
		this.owner = original.owner;
		this.direction = original.direction;
		this.speed = original.speed;
		this.ID = nextID++;
	}

	@Override
	public Vehicle clone() {
		return new Vehicle(this);
	}

}

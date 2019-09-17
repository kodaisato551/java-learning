package ch02.ex01;

public class Vehicle {

	private double speed;
	private double direction;
	private String owner;

	public Vehicle(double speed, double direction, String owner) {
		this.speed = speed;
		this.direction = direction;
		this.owner = owner;
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

}

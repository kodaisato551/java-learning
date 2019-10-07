package ch03.ex09;

public class Garage implements Cloneable {
	private Vehicle[] vehicles;
	private int vehicleNum;
	private int capacity;

	public Garage(int capacity) {
		vehicleNum = 0;
		this.capacity = capacity;
		vehicles = new Vehicle[capacity];
	}

	public void add(Vehicle vehicle) {
		if (capacity == vehicleNum) {
			throw new RuntimeException("Garage capacity is full.cannot add vehicle any more");
		}
		vehicles[vehicleNum++] = vehicle;
	}

	public Vehicle get(int index) {
		if (index > vehicleNum) {
			throw new IllegalArgumentException("Argument invalid");
		}
		return vehicles[index];
	}

	public static void main(String[] args) {
		Garage garage = new Garage(100);
		garage.add(new Vehicle("a"));

		Garage garageCloned = garage.clone();
		garageCloned.add(new Vehicle("b"));

		// garageに1台、garageClonedに2台入っていれば期待通り
		System.out.println("garage:" + garage.getVehicleNum());
		System.out.println("garageCloned:" + garageCloned.getVehicleNum());
	}

	public int getVehicleNum() {
		return vehicleNum;
	}

	@Override
	public Garage clone() {
		try {
			Garage objGarage = (Garage) super.clone();
			objGarage.vehicles = vehicles.clone();
			return objGarage;
		} catch (CloneNotSupportedException e) {
			throw new InternalError(e.toString());
		}
	}

}

package ch03.ex01;

public class PassengerVehicle extends Vehicle {
	private final int seatNum;
	private int passengerNum;

	public PassengerVehicle(String owner, int seatNum) {
		super(owner);
		this.seatNum = seatNum;
	}

	public int getPassengerNum() {
		return passengerNum;
	}

	/**
	 * 乗客の人数を確定します。
	 * セットする乗客の人数がシートのキャパシティを超えた場合にIllegalArgumentExceptionをスローします。
	 * @param passengerNum
	 * @throws IllegalArgumentException
	 */
	public void setPassengerNum(int passengerNum) throws IllegalArgumentException {
		if (passengerNum > seatNum) {
			throw new IllegalArgumentException("passengerNum must lareger than seatNum");
		}
		this.passengerNum = passengerNum;
	}

	@Override
	public String toString() {
		StringBuilder sBuilder = new StringBuilder(super.toString());
		sBuilder.append("seatNum : " + seatNum + "\n")
				.append("passengerNum : " + passengerNum + "\n");
		return sBuilder.toString();
	}

	public static void main(String[] args) {
		PassengerVehicle v1 = new PassengerVehicle("a", 5);
		System.out.println(v1);
//		PassengerVehicle v2 = new PassengerVehicle("b", 2);
//		System.out.println(v2);
	}
}

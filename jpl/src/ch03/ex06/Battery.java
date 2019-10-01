package ch03.ex06;

public class Battery extends EnergySource {

	private int batteryRemainingPercent;

	public Battery() {
		this.batteryRemainingPercent = 100;
	}

	public int getBatteryRemainingPercent() {
		return batteryRemainingPercent;
	}

	@Override
	boolean empty() {
		return this.batteryRemainingPercent == 0;
	}

	/**
	 * バッテリーを使用します.
	 *残量がマイナスになるような使用エネルギーを入力した場合falseを返し、フィールドは変更されない
	 *
	 * @param useagePercent
	 */
	public boolean useBattery(int useagePercent) {

		if (this.batteryRemainingPercent < useagePercent) {
			return false;
		} else {
			this.batteryRemainingPercent -= useagePercent;
			return true;
		}

	}

}

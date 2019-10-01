package ch03.ex06;

public class GasTank extends EnergySource {

	private int gasRemainingPercent;

	public GasTank() {
		this.gasRemainingPercent = 100;
	}

	public int getGasRemainingPercent() {
		return gasRemainingPercent;
	}

	@Override
	boolean empty() {
		return this.gasRemainingPercent == 0;
	}

	public boolean useGas(int useagePercent) {
		if (this.gasRemainingPercent < useagePercent) {
			return false;
		} else {
			this.gasRemainingPercent -= useagePercent;
			return true;
		}

	}

}

package ch03.ex03;

/**
 * TODO w:isa関係で対応するようにする
 * 回避策
 * 継承するのではなくフィールドとして持つようにした。
 */
public class YSolution {
	X x;
	protected int yMask = 0xff00;

	public YSolution() {
		x = new X();
		x.fullMask |= yMask;
	}

	public int mask(int orig) {
		return (orig & yMask);
	}

	protected int printFieldValue(String message) {
		x.printFieldValue(message);
		System.out.printf("yMask : %x\t\n\n", yMask);
		return 0;
	}

	public static void main(String[] args) {
		YSolution y_Solution = new YSolution();
		System.out.printf("%x\n", y_Solution.getMaskingResult());
	}

	public int getMaskingResult() {
		return x.getMaskingResult();
	}
}

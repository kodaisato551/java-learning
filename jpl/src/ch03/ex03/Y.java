package ch03.ex03;

/**
 *問題再現
 *SuperClass Xのコンストラクタでのmask呼び出しが
 *サブクラスyのmaskを使用している。このときyMask = 0(ｙのフィールド初期化すらされていないため？）なので
 *
 *本来maskigResultは0xaabbを0x00ffでマスクした0x00bbが望ましいが
 *継承すると0になる。
 */
public class Y extends X {
	protected int yMask = 0xff00;
	private final int tmp = printFieldValue("class Y : filed inicialization");

	{
		printFieldValue("class Y : inicialization block");
	}

	public Y() {
		fullMask |= yMask;
		printFieldValue("class Y : constractor");
	}

	/**
	 * 返却される整数値に意味はない
	 */
	@Override
	protected int printFieldValue(String message) {
		System.out.println("||  " + message + "  ||");
		System.out.printf("xMask : %x\t", xMask);
		System.out.printf("fullMask : %x\t", fullMask);
		System.out.printf("yMask : %x\t\n\n", yMask);
		return 0;
	}

	/**
	 * この時点でyMaskは0x00ff
	 */
	@Override
	public int mask(int orig) {
		return (orig & yMask);
	}

	public static void main(String[] args) {
		Y y = new Y();
		System.out.println(y.maskingResult);
	}

	@Override
	public int getMaskingResult() {
		return maskingResult;
	}

}

package ch03.ex02;

/**
 *
 * 1.Xのフィールドの初期化 > サブクラスYのprintFieldValueが呼ばれる
 * 2.X初期化block > サブクラスYのprintFieldValueが呼ばれる
 * 3.Xコンストラクタ > サブクラスYのprintFieldValueが呼ばれる
 * 4.
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

	public static void main(String[] args) {
		Y y = new Y();
	}

}

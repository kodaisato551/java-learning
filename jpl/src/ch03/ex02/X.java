package ch03.ex02;

public class X {
	protected int xMask = 0x00ff;
	protected int fullMask;
	private final int tmp = printFieldValue("class X : filed inicialization");

	{
		printFieldValue("class X : inicialization block");
	}

	public X() {
		fullMask = xMask;
		printFieldValue("class X : constractor");
	}

	public int mask(int orig) {
		return (orig & fullMask);
	}

	/**
	 * 返却される整数値に意味はない
	 * @param message
	 * @return
	 */
	protected int printFieldValue(String message) {
		System.out.println("||  " + message + "  ||");
		System.out.printf("xMask : %x\t", xMask);
		System.out.printf("fullMask : %x\t\n\n", fullMask);
		return 0;
	}

	public static void main(String[] args) {
		X x = new X();
		System.out.printf("%x\n", x.mask(0x013C));
	}
}

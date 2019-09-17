package ch01.ex13;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String mark;
		System.out.printf("1: %s\n", lo);

		for (int i = 2; i <= MAX_INDEX; i++) {
			mark = "";
			if (hi % 2 == 0) {
				mark = " *";
			}
			String string = hi + mark;
			System.out.printf("%d: %s\n", i, string);
			hi = lo + hi;
			lo = hi - lo;
		}

	}
}

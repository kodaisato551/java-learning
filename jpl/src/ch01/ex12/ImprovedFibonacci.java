package ch01.ex12;

public class ImprovedFibonacci {
	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		String[] array = new String[MAX_INDEX];

		array[0] = "1: " + lo;

		for (int i = 1; i < MAX_INDEX; i++) {
			String mark = "";
			if (hi % 2 == 0) {
				mark = " *";
			}
			array[i] = i + 1 + ": " + hi + mark;
			hi = lo + hi;
			lo = hi - lo;
		}

		for (String string : array) {
			System.out.println(string);
		}

	}
}

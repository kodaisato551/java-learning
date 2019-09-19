package ch01.ex09;

public class Fibonacci {
	static final int MAX_INTEGER = 9;

	public static void main(String[] args) {
		int[] array = new int[MAX_INTEGER];
		int lo = 1;
		int hi = 1;
		array[0] = lo;
		for (int i = 1; i < MAX_INTEGER; i++) {
			array[i] = hi;
			hi = lo + hi;
			lo = hi - lo;
		}

		for (int num : array) {
			System.out.println(num);
		}
	}
}

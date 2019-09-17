package ch01.ex10;

public class Fibonacci {

	static int MAX_INTEGER = 9;

	public static void main(String[] args) {
		int lo = 1;
		int hi = 1;
		EvenOddFibonacchi[] array = new EvenOddFibonacchi[MAX_INTEGER];

		array[0] = new EvenOddFibonacchi(lo);

		for (int i = 1; i < MAX_INTEGER; i++) {
			array[i] = new EvenOddFibonacchi(hi);
			hi = lo + hi;
			lo = hi - lo;
		}

		for (int i = 0; i < array.length; i++) {
			String mark = "";
			if (array[i].isEven) {
				mark = " *";
			}
			System.out.println(i + 1 + ": " + array[i].fiboncciNum + mark);
		}
	}

	private static class EvenOddFibonacchi {
		int fiboncciNum;
		boolean isEven;

		public EvenOddFibonacchi(int num) {
			fiboncciNum = num;
			isEven = num % 2 == 0;
		}

	}

}

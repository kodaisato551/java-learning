package ch01.ex06;

public class Fibonacci {
	static final String FIBO = "Fibonacci";

	public static void main(String[] args) {
		System.out.println(FIBO);
		int lo = 1;
		int hi = 1;
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // 新しいhi
			lo = hi - lo; /* 新しいloは、(合計 - 古いlo)
							  すなわち、古いhi */
		}
	}

}

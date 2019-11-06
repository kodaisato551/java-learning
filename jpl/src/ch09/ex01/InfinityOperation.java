package ch09.ex01;

/**
 *TODO w:test
 * @author Sato Kodai
 */
public class InfinityOperation {

	public static void main(String[] args) {
		System.out.println("----positive operation----");
		printFourArithmeticOperations(1.0);
		System.out.println();
		System.out.println("----negative operation----");
		printFourArithmeticOperations(-1.0);
		System.out.println();
		System.out.println("----inf operation----");
		printFourArithmeticOperations(Double.POSITIVE_INFINITY);
	}

	/**
	 * INFINITY & NEGATIVEINFINITYとnumの四則演算結果を標準出力する
	 * @param num
	 */
	public static void printFourArithmeticOperations(double num) {
		System.out.println("<positive infinity>");
		System.out.println(Double.POSITIVE_INFINITY + " + " + num + " = " + (Double.POSITIVE_INFINITY + num));
		System.out.println(Double.POSITIVE_INFINITY + " - " + num + " = " + (Double.POSITIVE_INFINITY - num));
		System.out.println(Double.POSITIVE_INFINITY + " * " + num + " = " + (Double.POSITIVE_INFINITY * num));
		System.out.println(Double.POSITIVE_INFINITY + " / " + num + " = " + (Double.POSITIVE_INFINITY / num));

		System.out.println("<negative infinity>");
		System.out.println(Double.NEGATIVE_INFINITY + " + " + num + " = " + (Double.NEGATIVE_INFINITY + num));
		System.out.println(Double.NEGATIVE_INFINITY + " - " + num + " = " + (Double.NEGATIVE_INFINITY - num));
		System.out.println(Double.NEGATIVE_INFINITY + " * " + num + " = " + (Double.NEGATIVE_INFINITY * num));
		System.out.println(Double.NEGATIVE_INFINITY + " / " + num + " = " + (Double.NEGATIVE_INFINITY / num));
	}
}

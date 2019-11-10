package ch10.ex04;

/**
 * ch01.ex04
 *  do-whileでもかける
 * @author Sato Kodai
 *
 */
public class Squre {
	static final int UPPER_LIMIT = 10;

	public static void main(String[] args) {
		//		for (int i = 0; i < UPPER_LIMIT; i++) {
		//			System.out.println(i * i);
		//		}
		int i = 0;
		while (i < UPPER_LIMIT) {
			System.out.println(i * i);
			i++;
		}

		//		do {
		//			System.out.println(i * i);
		//			i++;
		//		} while (i < UPPER_LIMIT);
	}
}

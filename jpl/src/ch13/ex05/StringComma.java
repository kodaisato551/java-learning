package ch13.ex05;

import java.util.Objects;

public class StringComma {
	/**
	 * degit桁目にコンマを打つ
	 */
	public static String stringComma(String input, int degit) {
		Objects.requireNonNull(input, "input Strig must be not null");
		int offset = degit;
		StringBuilder sBuilder = new StringBuilder(input);
		sBuilder.reverse();
		while (offset < sBuilder.length()) {
			sBuilder.insert(offset, ",");
			offset = 1 + offset + degit;
		}
		return sBuilder.reverse().toString();
	}

	public static void main(String[] args) {
		String string = "hog3";

		System.out.println(stringComma(string, 3));

	}
}

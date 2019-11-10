package ch10.ex05;

public class CharSpacing {
	/**
	 *二つのcharを受け取りその間の文字をStringとして返す。
	 * @return
	 */
	public static String charSpacing(char c1, char c2) {
		StringBuilder sb = new StringBuilder();
		for (char c = ++c1; c < c2; c++) {
			sb.append(c);
		}
		return sb.toString();
	}

}

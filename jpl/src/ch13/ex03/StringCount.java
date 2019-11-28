package ch13.ex03;

import java.util.ArrayList;
import java.util.List;

public class StringCount {
	/**
	 * 本文中のdelimitedString メソッドは、１つの区切られた入力文字列に
	 * １つの区切られた文字列を取り出して
	 *TODO w:ロジックを深く考える
	 * @param from
	 * @param start
	 * @param end
	 * @return
	 */
	public static String[] delimitedString(String from, char start, char end) {
		int startPos = from.indexOf(start);
		int endPos = from.indexOf(end);
		List<String> strings = new ArrayList<String>();

		while (true) {
			if (startPos == -1) {
				break;
			} else if (endPos == -1) {
				strings.add(from.substring(startPos));
				break;
			} else if (startPos > endPos) {
				endPos = from.indexOf(end, endPos + 1);
			} else {
				strings.add(from.substring(startPos, endPos + 1));
				startPos = from.indexOf(start, startPos + 1);

			}
		}

		return strings.toArray(new String[strings.size()]);
	}

}

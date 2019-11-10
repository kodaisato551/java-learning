package ch10.ex01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO M:/ddd対応
 * @author Sato Kodai
 *
 */
public class Replace {

	/**
	 * エスケープシーケンスも文字列として表示可能にする
	 \dddはできませんでした。
	 * @param str
	 * @return
	 */
	public static String replace(String str) {
		StringBuilder sb = new StringBuilder();
		for (char c : str.toCharArray()) {
			if (c == '\n') {
				sb.append("\\n");
			} else if (c == '\t') {
				sb.append("\\t");
			} else if (c == '\b') {
				sb.append("\\b");
			} else if (c == '\r') {
				sb.append("\\r");
			} else if (c == '\f') {
				sb.append("\\f");
			} else if (c == '\\') {
				sb.append("\\\\");
			} else if (c == '\'') {
				sb.append("\\'");
			} else if (c == '\"') {
				sb.append("\\\"");
			} else {
				sb.append(c);
			}
		}
		str = sb.toString();
		//\ddd
		Matcher m = Pattern.compile("\\[0-7][0-7][0-7]").matcher(str);
		if (m.find()) {
			System.out.println("match");
			str = "\\" + m.toString();
		}
		return str;
	}

}

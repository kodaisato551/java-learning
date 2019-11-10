package ch10.ex02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO M:/ddd対応
 * switch caseに書き直す
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

			switch (c) {
			case '\n':
				sb.append("\\n");
				break;
			case '\t':
				sb.append("\\t");
				break;
			case '\b':
				sb.append("\\b");
				break;
			case '\r':
				sb.append("\\r");
				break;
			case '\f':
				sb.append("\\f");
				break;
			case '\\':
				sb.append("\\\\");
				break;
			case '\'':
				sb.append("\\'");
				break;
			case '\"':
				sb.append("\\\"");
				break;
			default:
				sb.append(c);
				break;
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

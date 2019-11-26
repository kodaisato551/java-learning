package ch13.ex01;

import java.util.regex.Pattern;

public class StringCount {
	/**
	 *
	 * @param str
	 * @param key
	 * @return 見つからなかった場合０を返します
	 */
	public static int count(String str, String key) {
		return (int) Pattern.compile(key).splitAsStream(str).count() - 1;
	}
}

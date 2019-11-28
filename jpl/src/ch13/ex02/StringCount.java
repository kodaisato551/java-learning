package ch13.ex02;

import java.util.regex.Pattern;

public class StringCount {
	/**
	 * TODO M:indexofを使用する
	 *ch13.ex01と同様
	 * @param str
	 * @param key
	 * @return 見つからなかった場合０を返します
	 */
	public static int count(String str, String key) {
		return (int) Pattern.compile(key).splitAsStream(str).count() - 1;
	}
}

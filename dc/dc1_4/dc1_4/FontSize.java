package dc1_4;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum FontSize {
	SMALL("Small", 20), MEDIUM("Medium", 50), LARGE("Large", 80);
	private int size;
	private String name;

	private FontSize(String name, int size) {
		this.size = size;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	/**
	 * smallなどのKEYからフォントサイズを取得するメソッド
	 */
	public static int keyToIntSize(String key) {
		return keyToEnum.get(key).getSize();
	}

	public static FontSize keyToFontSize(String key) {
		return keyToEnum.get(key);
	}

	public static Set<String> getKeys() {
		return keyToEnum.keySet();
	}

	private final static Map<String, FontSize> keyToEnum = new HashMap<String, FontSize>() {
		{
			for (FontSize fontSize : FontSize.values()) {
				put(fontSize.getName(), fontSize);
			}
		}
	};

}

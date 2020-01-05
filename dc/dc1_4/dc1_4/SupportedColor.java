package dc1_4;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum SupportedColor {
	BLACK("Black", Color.BLACK), BLUE("Blue", Color.BLUE), CYAN("Cyan", Color.CYAN), DARKGRAY("DarkGray",
			Color.DARK_GRAY), GRAY("Gray", Color.GRAY), GREEN("Green",
					Color.GREEN), LIGHTGRAY("LightGray", Color.LIGHT_GRAY), WHITE("White", Color.WHITE);

	private Color color;
	private String name;

	private SupportedColor(String name, Color color) {
		this.name = name;
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public Color getColor() {
		return color;
	}

	public static Color getColor(String key) {
		return keyToEnum.get(key).getColor();
	}

	public static Set<String> getKeys() {
		return keyToEnum.keySet();
	}

	private final static Map<String, SupportedColor> keyToEnum = new HashMap<String, SupportedColor>() {
		{
			for (SupportedColor fontSize : SupportedColor.values()) {
				put(fontSize.getName(), fontSize);
			}
		}
	};
}

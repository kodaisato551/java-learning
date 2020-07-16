package dc2_4.setting;

import dc2_3.setting.DefaultProperties;

import java.awt.*;
import java.util.List;
import java.util.*;

import static dc2_4.setting.DefaultProperties.BG_COLORLISTITEM;
import static dc2_4.setting.DefaultProperties.FONT_COLORLISTITEM;

public class SupportedSettings {
    public static final List<String> FONT_TYPE_LIST = Arrays
            .asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

    // Font size
    public static final Map<String, Integer> FONT_SIZE_MAP = new HashMap<>();


    // Supported color
    public static final Map<String, Color> SUPPORTED_COLOR = new HashMap<>();
    public static final List<ColorListItem> COLOR_LIST_ITEMS = new ArrayList<>();
    public static final Map<Color, String> COLOR_VS_STRING_MAP = new HashMap<>();

    static {
        FONT_SIZE_MAP.put("Small", DefaultProperties.WINDOW_FONT_SIZE / 2);
        FONT_SIZE_MAP.put("Medium", DefaultProperties.WINDOW_FONT_SIZE);
        FONT_SIZE_MAP.put("Large", DefaultProperties.WINDOW_FONT_SIZE * 2);

        SUPPORTED_COLOR.put("Black", Color.BLACK);
        SUPPORTED_COLOR.put("White", Color.WHITE);
        SUPPORTED_COLOR.put("Gray", Color.GRAY);
        SUPPORTED_COLOR.put("Blue", Color.BLUE);
        SUPPORTED_COLOR.put("Cyan", Color.CYAN);
        SUPPORTED_COLOR.put("Dark Gray", Color.DARK_GRAY);
        SUPPORTED_COLOR.put("Green", Color.GREEN);
        SUPPORTED_COLOR.put("Yellow", Color.YELLOW);
        SUPPORTED_COLOR.put("Magenta", Color.MAGENTA);
        SUPPORTED_COLOR.put("Light Gray", Color.LIGHT_GRAY);
        SUPPORTED_COLOR.put("Orange", Color.ORANGE);
        SUPPORTED_COLOR.put("Red", Color.RED);
        SUPPORTED_COLOR.put("Pink", Color.PINK);

        for (Map.Entry<String, Color> entry : SupportedSettings.SUPPORTED_COLOR.entrySet()) {
            if (entry.getValue().equals(Color.BLACK)) {
                COLOR_LIST_ITEMS.add(FONT_COLORLISTITEM);
            } else if (entry.getValue().equals(Color.WHITE)) {
                COLOR_LIST_ITEMS.add(BG_COLORLISTITEM);

            } else {
                COLOR_LIST_ITEMS.add(new ColorListItem(entry.getValue(), entry.getKey()));
            }
            COLOR_VS_STRING_MAP.put(entry.getValue(), entry.getKey());
        }
    }
}

package dc3_4;

import java.awt.*;
import java.util.List;
import java.util.*;

public class SupportedSettings {
    public static final List<String> FONT_TYPE_LIST = Arrays
            .asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    public static final List<String> FONT_SIZE = new ArrayList<>();

    // Font size setting
    public static final Map<String, Integer> FONT_SIZE_MAP = new HashMap<>();
    

    public static final Map<String, Color> COLOR_MAP = new HashMap<>();

    static {
        FONT_SIZE_MAP.put("Small", DefaultProperties.WINDOW_FONT_SIZE / 2);
        FONT_SIZE_MAP.put("Medium", DefaultProperties.WINDOW_FONT_SIZE);
        FONT_SIZE_MAP.put("Large", DefaultProperties.WINDOW_FONT_SIZE * 2);

        FONT_SIZE.addAll(FONT_SIZE_MAP.keySet());

        COLOR_MAP.put("White", Color.WHITE);
        COLOR_MAP.put("LightGray", Color.WHITE);
        COLOR_MAP.put("Gray", Color.GRAY);
        COLOR_MAP.put("DarkGray", Color.DARK_GRAY);
    }


}

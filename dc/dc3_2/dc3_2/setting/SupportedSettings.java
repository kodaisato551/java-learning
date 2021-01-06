package dc3_2.setting;

import java.awt.*;
import java.util.List;
import java.util.*;

public class SupportedSettings {
    public static final List<String> FONT_TYPE_LIST = Arrays
            .asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
    public static final List<String> FONT_SIZE = new ArrayList<>();

    static {
        for (int i = 1; i < 1000; i++) {
            FONT_SIZE.add("" + i);
        }
    }


    public static final Map<String, Color> COLOR_MAP = new HashMap<>();

    static {
        COLOR_MAP.put("White", Color.WHITE);
        COLOR_MAP.put("LightGray", Color.WHITE);
        COLOR_MAP.put("Gray", Color.GRAY);
        COLOR_MAP.put("DarkGray", Color.DARK_GRAY);
    }


}

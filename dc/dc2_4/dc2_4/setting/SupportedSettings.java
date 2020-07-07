package dc2_4.setting;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupportedSettings {
    public static final List<String> FONT_TYPE_LIST = Arrays
            .asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());


    public static final Map<String, Color> COLOR_MAP = new HashMap<>();

    static {
        COLOR_MAP.put("White", Color.WHITE);
        COLOR_MAP.put("LightGray", Color.WHITE);
        COLOR_MAP.put("Gray", Color.GRAY);
        COLOR_MAP.put("DarkGray", Color.DARK_GRAY);
    }


}

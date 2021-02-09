import javafx.scene.paint.Color;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SupportedSettings {

    // Font type setting
    public static final List<String> FONT_TYPE_LIST = Arrays
            .asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());


    // Font size setting
    public static final Map<String, Integer> FONT_SIZE_MAP = new HashMap<>();

    // Supported color
    public static final Map<String, Color> SUPPORTED_COLOR = new HashMap<>();


    static {
        FONT_SIZE_MAP.put("Small", DefaultProperties.WINDOW_FONT_SIZE / 2);
        FONT_SIZE_MAP.put("Medium", DefaultProperties.WINDOW_FONT_SIZE);
        FONT_SIZE_MAP.put("Large", DefaultProperties.WINDOW_FONT_SIZE * 2);

        SUPPORTED_COLOR.put("Black", Color.BLACK);
        SUPPORTED_COLOR.put("White", Color.WHITE);
        SUPPORTED_COLOR.put("Gray", Color.GRAY);
        SUPPORTED_COLOR.put("Blue", Color.BLUE);
        SUPPORTED_COLOR.put("Cyan", Color.CYAN);
        SUPPORTED_COLOR.put("Green", Color.GREEN);
        SUPPORTED_COLOR.put("Yellow", Color.YELLOW);
        SUPPORTED_COLOR.put("Magenta", Color.MAGENTA);
        SUPPORTED_COLOR.put("Orange", Color.ORANGE);
        SUPPORTED_COLOR.put("Red", Color.RED);
        SUPPORTED_COLOR.put("Pink", Color.PINK);
    }


}

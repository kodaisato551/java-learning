package dc2_2.setting;

import java.awt.*;


public class DefaultProperties {

    public static final int DEFAULT_WINDOW_WIDTH = 400;
    public static final int DEFAULT_WINDOW_HEIGHT = 200;
    public static final int DEFAULT_WINDOW_FONT_SIZE = Math.min(DEFAULT_WINDOW_HEIGHT, DEFAULT_WINDOW_WIDTH) / 3;

    public static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, DEFAULT_WINDOW_FONT_SIZE);
    public static final Color DEFAULT_FONT_COLOR = Color.BLACK;
    public static final Color DEFAULT_BG_COLOR = Color.WHITE;

    private DefaultProperties() {
    }
}

package dc2_2.setting;

import java.awt.*;


public class DefaultProperties {

    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 200;
    public static final int WINDOW_FONT_SIZE = Math.min(WINDOW_HEIGHT, WINDOW_WIDTH) / 3;

    public static final Font FONT = new Font("Arial", Font.BOLD, WINDOW_FONT_SIZE);
    public static final Color FONT_COLOR = Color.BLACK;
    public static final Color BG_COLOR = Color.WHITE;

    public static final int FONT_TYPE_INDEX = SupportedSettings.FONT_TYPE_LIST.indexOf("Arial");

    private DefaultProperties() {
    }
}

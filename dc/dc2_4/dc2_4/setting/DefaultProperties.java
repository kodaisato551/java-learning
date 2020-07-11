package dc2_4.setting;

import java.awt.*;


public class DefaultProperties {

    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 200;
    public static final int PANEL_WIDTH = 384;
    public static final int PANEL_HEIGHT = 141;
    public static final int WINDOW_FONT_SIZE = Math.min(WINDOW_HEIGHT, WINDOW_WIDTH) / 3;

    public static final Font FONT = new Font("Arial", Font.BOLD, WINDOW_FONT_SIZE);
    public static final Color FONT_COLOR = Color.BLACK;
    public static final Color BG_COLOR = Color.WHITE;

    public static final int FONT_TYPE_INDEX = SupportedSettings.FONT_TYPE_LIST.indexOf("Arial");
    public static final int FONT_COLOR_INDEX = SupportedSettings.FONT_TYPE_LIST.indexOf("Black");
    public static final int BG_COLOR_INDEX = SupportedSettings.FONT_TYPE_LIST.indexOf("White");
    private DefaultProperties() {
    }
}

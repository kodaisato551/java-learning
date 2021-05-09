import java.awt.*;

class DefaultSetting {

    static final int DEFAULT_WINDOW_WIDTH = 400;
    static final int DEFAULT_WINDOW_HEIGHT = 200;
    private static final int DEFAULT_WINDOW_FONT_SIZE = Math.min(DEFAULT_WINDOW_HEIGHT, DEFAULT_WINDOW_WIDTH) / 3;

    static final Font DEFAULT_FONT = new Font("Arial", Font.BOLD, 20);

    private DefaultSetting() {
    }
}

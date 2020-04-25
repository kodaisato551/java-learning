package dc2_2.setting;

import java.awt.*;

public class Setting {
    private Color fontColor;
    private Color bgColor;
    private Font currentFont;

    private static final Setting currentSetting = new Setting();

    private Setting() {
        currentFont = DefaultProperties.DEFAULT_FONT;
        fontColor = Color.BLACK;
        bgColor = Color.WHITE;
    }

    public static Setting getInstance() {
        return currentSetting;
    }

    public void setFont(String fontType, int size) {
        currentFont = new Font(fontType, currentFont.getStyle(), size);
    }

    public Font getCurrentFont() {
        return currentFont;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }
}

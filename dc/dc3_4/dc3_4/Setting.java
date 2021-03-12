package dc3_4;

import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

public class Setting {

    private String fontStyle;
    private int fontSize;
    private Color fontColor;
    private Color bgColor;

    private static final Setting instance = new Setting();

    public static Setting getInstance() {
        return instance;
    }

    private Setting() {
        changeDefaultSetting();
    }

    public String getFontStyle() {
        return fontStyle;
    }

    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public void changeDefaultSetting() {
        fontStyle = "Arial";
        fontSize = DefaultProperties.WINDOW_FONT_SIZE;
        fontColor = Color.BLACK;
        bgColor = Color.WHITE;
        calcPreferredWindowSize();
    }

    public Dimension2D calcPreferredWindowSize() {
        double width = fontSize * 12;
        double height = fontSize * 8;
        return new Dimension2D(width, height);
    }
}



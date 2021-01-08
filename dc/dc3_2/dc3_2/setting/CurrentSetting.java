package dc3_2.setting;

import javafx.scene.paint.Color;

public class CurrentSetting {

    private String fontStyle;
    private int fontSize;
    private Color fontColor;
    private Color bgColor;

    private static final CurrentSetting instance = new CurrentSetting();

    public static CurrentSetting getInstance() {
        return instance;
    }

    private CurrentSetting() {
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
        fontSize = 30;
        fontColor = Color.BLACK;
        bgColor = Color.WHITE;
    }

}

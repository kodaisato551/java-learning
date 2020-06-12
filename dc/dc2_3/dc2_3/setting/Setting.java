package dc2_3.setting;

import java.awt.*;

public class Setting {
    private Color fontColor;
    private Color bgColor;
    private Font currentFont;
    private int selectedFontTypeIndex;//FONT_TYPE_LISTの中の現在の設定のインデックス

    private static final Setting currentSetting = new Setting();

    private Setting() {
        changeDefaultSetting();
    }

    public static Setting getInstance() {
        return currentSetting;
    }

    public void setFont(String fontType, int size) {
        currentFont = new Font(fontType, currentFont.getStyle(), size);
    }

    public void setFontType(String typeName) {
        currentFont = new Font(typeName, currentFont.getStyle(), currentFont.getSize());
    }

    public void setFontSize(int size) {
        currentFont = new Font(currentFont.getFontName(), currentFont.getStyle(), size);
    }

    public Font getCurrentFont() {
        return currentFont;
    }

    public Color getFontColor() {
        return fontColor;
    }

    public void setSelectedFontTypeIndex(int selectedFontTypeIndex) {
        this.selectedFontTypeIndex = selectedFontTypeIndex;
    }

    public int getSelectedFontTypeIndex() {
        return selectedFontTypeIndex;
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

    public void changeDefaultSetting() {
        currentFont = DefaultProperties.FONT;
        fontColor = DefaultProperties.FONT_COLOR;
        bgColor = DefaultProperties.BG_COLOR;
        selectedFontTypeIndex = DefaultProperties.FONT_TYPE_INDEX;
    }
}

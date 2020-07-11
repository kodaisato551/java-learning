package dc2_4.setting;

import java.awt.*;

public class Setting {
    private static final Setting currentSetting = new Setting();
    private Color fontColor;
    private Color bgColor;
    private Font currentFont;
    private int selectedFontTypeIndex;//FONT_TYPE_LISTの中の現在の設定のインデックス
    private int selectedFontColorIndex;
    private int selectedBgColorIndex;

    private Setting() {
        changeDefaultSetting();
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

    public void setFontColor(Color fontColor) {
        this.fontColor = fontColor;
    }

    public int getSelectedFontColorIndex() {
        return selectedFontColorIndex;
    }

    public void setSelectedFontColorIndex(int selectedFontColorIndex) {
        this.selectedFontColorIndex = selectedFontColorIndex;
    }

    public int getSelectedBgColorIndex() {
        return selectedBgColorIndex;
    }

    public void setSelectedBgColorIndex(int selectedBgColorIndex) {
        this.selectedBgColorIndex = selectedBgColorIndex;
    }

    public int getSelectedFontTypeIndex() {
        return selectedFontTypeIndex;
    }

    public void setSelectedFontTypeIndex(int selectedFontTypeIndex) {
        this.selectedFontTypeIndex = selectedFontTypeIndex;
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public void changeDefaultSetting() {
        currentFont = DefaultProperties.FONT;
        fontColor = DefaultProperties.FONT_COLOR;
        bgColor = DefaultProperties.BG_COLOR;
        selectedFontTypeIndex = DefaultProperties.FONT_TYPE_INDEX;
        selectedFontColorIndex = DefaultProperties.FONT_COLOR_INDEX;
        selectedBgColorIndex = DefaultProperties.BG_COLOR_INDEX;
    }
}

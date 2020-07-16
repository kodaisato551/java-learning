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

    private Point currentPoint;

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
        selectedFontColorIndex = SupportedSettings.COLOR_LIST_ITEMS.indexOf(DefaultProperties.FONT_COLORLISTITEM);
        selectedBgColorIndex = SupportedSettings.COLOR_LIST_ITEMS.indexOf(DefaultProperties.BG_COLORLISTITEM);
        currentPoint = new Point(0, 0);
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Point currentPoint) {
        this.currentPoint = currentPoint;
    }

    /**
     * settingのステータスを表示
     */
    public void printStatus(String title) {
        System.out.println("$$$$$$$ " + title + " $$$$$$$");
        System.out.println("Font Type : " + currentFont.getFamily());
        System.out.println("Font Size : " + currentFont.getSize());
        System.out.println("Font Color : " + SupportedSettings.COLOR_VS_STRING_MAP.get(fontColor));
        System.out.println("Bg Color : " + SupportedSettings.COLOR_VS_STRING_MAP.get(bgColor));
        System.out.println("Point X : " + currentPoint.x);
        System.out.println("Point Y : " + currentPoint.y);
    }


}

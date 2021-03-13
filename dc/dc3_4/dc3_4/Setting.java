package dc3_4;

import dc2_4.setting.SupportedSettings;
import javafx.geometry.Dimension2D;
import javafx.scene.paint.Color;

public class Setting {

    private String fontStyle;
    private int fontSize;
    private Color fontColor;
    private Color bgColor;

    private static final Setting instance = new Setting();
    private Dimension2D currentPoint;

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

    public Dimension2D getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Dimension2D currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Dimension2D calcPreferredWindowSize() {
        double width = fontSize * 12;
        double height = fontSize * 8;
        return new Dimension2D(width, height);
    }

    /**
     * settingのステータスを表示
     */
    public void printStatus(String title) {
        System.out.println("$$$$$$$ " + title + " $$$$$$$");
        System.out.println("Font Type : " + fontStyle);
        System.out.println("Font Size : " + fontSize);
        System.out.println("Font Color : " + dc2_4.setting.SupportedSettings.COLOR_VS_STRING_MAP.get(fontColor));
        System.out.println("Bg Color : " + SupportedSettings.COLOR_VS_STRING_MAP.get(bgColor));
        System.out.println("Point X : " + currentPoint.getWidth());
        System.out.println("Point Y : " + currentPoint.getHeight());
    }

}



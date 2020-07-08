package dc2_4.setting;

import java.awt.*;

public class ColorListItem {
    private Color color;
    private String colorName;

    public ColorListItem(Color color, String colorName) {
        this.color = color;
        this.colorName = colorName;
    }

    public Color getColor() {
        return color;
    }

    public String getColorName() {
        return colorName;
    }
}

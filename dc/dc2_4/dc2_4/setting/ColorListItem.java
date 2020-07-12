package dc2_4.setting;

import java.awt.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ColorListItem that = (ColorListItem) o;
        return Objects.equals(color, that.color) &&
                Objects.equals(colorName, that.colorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, colorName);
    }
}

package ch03.ex05;

import javafx.scene.paint.Color;

public interface ColorTransformer {
    /**
     * Replace color in Image at position x and y
     *
     * @param x             position X in Image
     * @param y             position Y in Image
     * @param replacedColor replaced color
     * @return
     */
    Color apply(int x, int y, Color replacedColor);
}

package ch03.ex10;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

/**
 * なぜ、 次 の 呼び出し が でき ない の でしょ う か。
 */
public class Main {
    public static void main(String[] args) {
        UnaryOperator<Color> op = Color::brighter;
        Image image = null;
//        Image finalImage = transform(image, op.compose(Color::grayscale));
        /*第二引数の型が異なるため
        UnaryOperator<Color>が必要であるが、compase()の戻り値はFunction<V,Color>なので型が不整合
         */

    }

    public static Image transform(Image in, UnaryOperator<Color> transformer) {
        int height = (int) in.getHeight();
        int width = (int) in.getWidth();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, transformer.apply(in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }
}

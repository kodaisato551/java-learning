package ch03.ex12;

import ch03.ex11.ColorTransformer;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

/**
 * 3. 6 節「 遅延」 の LatentImage を 機能 拡張 し て、 UnaryOperator < Color > と ColorTransformer の 両方 を サポート する よう に し なさい。
 * ヒント： UnaryOperator < Color > を ColorTransformer へ 適応 さ せ なさい。
 */
public class LatentImage {
    private Image in;
    private List<ColorTransformer> transformers = new ArrayList<>();

    LatentImage(Image in) {
        this.in = in;
    }

    public Image toImage() {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++)
            for (int y = 0; y < height; y++) {
                Color c = in.getPixelReader().getColor(x, y);
                for (ColorTransformer f : transformers) c = f.apply(x, y, c);
                out.getPixelWriter().setColor(x, y, c);
            }
        return out;
    }


    static LatentImage from(Image in) {
        return new LatentImage(in);
    }

    LatentImage transform(ColorTransformer transformer) {
        transformers.add(transformer);
        return this;
    }

    LatentImage transform(UnaryOperator<Color> operator) {
        return transform(toColorTransformer(operator));
    }

    static ColorTransformer toColorTransformer(UnaryOperator<Color> operator) {
        return (x, y, c) -> operator.apply(c);
    }

}

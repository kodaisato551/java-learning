package ch03.ex15;

import ch03.ex11.ColorTransformer;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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

    static LatentImage from(Image in) {
        return new LatentImage(in);
    }

    static ColorTransformer toColorTransformer(UnaryOperator<Color> operator) {
        return (x, y, c) -> operator.apply(c);
    }

    public static Color[][] parallelTransform(Color[][] in, UnaryOperator<Color> f) {
        int n = Runtime.getRuntime().availableProcessors();
        int height = in.length;
        int width = in[0].length;
        Color[][] out = new Color[height][width];
        try {
            ExecutorService pool = Executors.newCachedThreadPool();
            for (int i = 0; i < n; i++) {
                int fromY = i * height / n;
                int toY = (i + 1) * height / n;
                pool.submit(() -> {
                    for (int x = 0; x < width; x++) for (int y = fromY; y < toY; y++) out[y][x] = f.apply(in[y][x]);
                });
            }
            pool.shutdown();
            pool.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        return out;
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

    LatentImage transform(ColorTransformer transformer) {
        transformers.add(transformer);
        return this;
    }

    LatentImage transform(UnaryOperator<Color> operator) {
        return transform(toColorTransformer(operator));
    }
}

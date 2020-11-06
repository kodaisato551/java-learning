package ch03.ex11;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

/**
 * 2 つ の ColorTransformer オブジェクト を 合成 できる static メソッド を 実装 し なさい。
 * そして、 x 座標 と y 座標 を 無視 する ColorTransformer へ UnaryOperator < Color > を 変える static メソッド を 実装 し なさい。
 * それから、 変換 によって 明るく なっ た 画像 に 灰色 の 枠 を 追加 する ため に、 実装 し た メソッド を 使用 し なさい（ 灰色 の 枠 に関して は 練習 問題 5 を 参照 し なさい）。
 */
public class ImageProcessor {

    public static Image transform(Image in, ColorTransformer f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(x, y, Color.BLACK));
            }
        }
        return out;
    }

    /**
     * 指定した座標が
     *
     * @param in
     * @return
     */
    public static boolean isFrame(Image in, int x, int y, int frameSize) {
        int height = (int) in.getHeight();
        int width = (int) in.getWidth();
        if (x <= frameSize || x >= width - frameSize || y <= frameSize || y >= height - frameSize) {
            return true;
        }
        return false;
    }

    public static ColorTransformer combine(ColorTransformer tr1, ColorTransformer tr2) {
        return (x, y, transColor) -> {
            Color color = tr1.apply(x, y, transColor);
            return tr2.apply(x, y, color);
        };
    }

    /**
     * unaryoperatorからcolortransformweへの変換
     *
     * @return
     */
    public static ColorTransformer toColorTransformer(UnaryOperator<Color> unaryOperator) {
        return (x, y, color) -> unaryOperator.apply(color);
    }


}

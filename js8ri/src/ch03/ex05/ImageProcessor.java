package ch03.ex05;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

/**
 * 次 は、 ColorTransformer の 具体 例 です。 次 の よう に、 画像 の 周り に 枠 を 付加 し ます。
 * 最初 に、 3. 3 節「 関数 型 インタフェース の 選択」 の transform メソッド を、 UnaryOperator < Color > の 代わり に ColorTransformer で 実装 し なさい。
 * それから、 画像 の 周り の 10 ピクセル を 灰色 の 枠 で 置き換える ため に、 その transform メソッド を 適切 な ラムダ 式 で 呼び出し なさい。
 */
public class ImageProcessor {

    public static Image transform(Image in, UnaryOperator<Color> f) {
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
            }
        }
        return out;
    }
}

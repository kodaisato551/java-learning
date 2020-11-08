package ch03.ex13;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

/**
 * ぼやけ 検出、 あるいは、 エッジ 検出 といった 畳み込み フィルター は、 隣接 する ピクセル から 1 つ の ピクセル を 計算 し ます。
 * 画像 を ぼやかす ため には、 ピクセル と その 隣接 する 8 個 の ピクセル の 平均 で、 個々 の 色 値 を 置き換え ます。
 * エッジ 検出 には、 個々 の 色 値 c を 4 c - n - e - s - w で 置き換え ます。
 * ここ で、 他 の 色 は、 北（ north）、 東（ east）、 南（ south）、 西（ west） の ピクセル の 色 値 です。
 * これ は、 3. 6 節「 遅延」 で 説明 さ れ た 方法 を 用い た 遅延 では 実装 でき ない こと に 注意 し て ください。
 * なぜなら、 計算 する ため に、 前段 の 画像（ あるいは、 少なくとも 隣接 する ピクセル） が 必要 だ から です。 これら の 操作 を 扱う ため に 遅延 画像処理 の
 * 機能 を 強化 し なさい。 これら の 演算 の 1 つ が 評価 さ れる 際 に、 前段 の 計算 が 強制 さ れる よう に し なさい。
 *
 *
 * TODO わかりませんでした。
 */
public class ImageProcessor {
    public Image transform(Image in,UnaryOperator<Color> f){
        int width = (int) in.getWidth();
        int height = (int) in.getHeight();
        WritableImage out = new WritableImage(width, height);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                out.getPixelWriter().setColor(x, y, f.apply(Color.BLACK));
            }
        }
        return out;
    }

    private Color medianColor(Image in,int x,int y){
        Color c = in.getPixelReader().getColor(x,y);
        return null;
    }
}

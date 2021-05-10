import java.awt.*;

public class Racket {
    // ラケットのサイズ
    public static final int WIDTH = 80;
    public static final int HEIGHT = 5;

    // ラケットの中心位置
    private int centerPos;

    public Racket() {
        // ラケットの位置を画面の真ん中で初期化
        centerPos = MainPanel.WIDTH / 2;
    }

    /**
     * ラケットの描画
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(centerPos - WIDTH / 2, MainPanel.HEIGHT - HEIGHT,
                WIDTH, HEIGHT);
    }

    /**
     * ラケットの移動
     *
     * @param pos 移動先座標
     */
    public void move(int pos) {
        centerPos = pos;

        // ラケットが画面の端から飛び出ないようにする
        if (centerPos < WIDTH / 2) { // 左端
            centerPos = WIDTH / 2;
        } else if (centerPos > MainPanel.WIDTH - WIDTH / 2) { // 右端
            centerPos = MainPanel.WIDTH - WIDTH / 2;
        }
    }

}

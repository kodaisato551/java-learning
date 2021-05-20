import java.awt.*;


public class Block {
    public static final int WIDTH = 40;
    public static final int HEIGHT = 16;
    private final int x;
    private final int y;
    private boolean isDeleted;

    private final Rectangle block;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        block = new Rectangle(x, y, WIDTH, HEIGHT);
        isDeleted = false;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    /**
     * ブロックを描画
     *
     * @param g
     */
    public void draw(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, WIDTH, HEIGHT);

        // 枠線を描画
        g.setColor(Color.BLACK);
        g.drawRect(x, y, WIDTH, HEIGHT);
    }

    /**
     * ブロックを消去
     */
    public void delete() {
        isDeleted = true;
    }

    /**
     * ボールとの衝突
     * 　返り値としては
     *
     * @param ball
     */
    public Direction collideWith(Ball ball) {
        int ballX = ball.getX();
        int ballY = ball.getY();
        int ballSize = ball.getSize();
        if (block.contains(ballX, ballY)
                && block.contains(ballX + ballSize, ballY)) {
            // ブロックの下から衝突＝ボールの左上・右上の点がブロック内
            return Direction.DOWN;
        } else if (block.contains(ballX + ballSize, ballY)
                && block.contains(ballX + ballSize, ballY + ballSize)) {
            // ブロックの左から衝突＝ボールの右上・右下の点がブロック内
            return Direction.LEFT;
        } else if (block.contains(ballX, ballY)
                && block.contains(ballX, ballY + ballSize)) {
            // ブロックの右から衝突＝ボールの左上・左下の点がブロック内
            return Direction.RIGHT;
        } else if (block.contains(ballX, ballY + ballSize)
                && block.contains(ballX + ballSize, ballY + ballSize)) {
            // ブロックの上から衝突＝ボールの左下・右下の点がブロック内
            return Direction.UP;
        } else if (block.contains(ballX + ballSize, ballY)) {
            // ブロックの左下から衝突＝ボールの右上の点がブロック内
            return Direction.DOWN_LEFT;
        } else if (block.contains(ballX, ballY)) {
            // ブロックの右下から衝突＝ボールの左上の点がブロック内
            return Direction.DOWN_RIGHT;
        } else if (block.contains(ballX + ballSize, ballY + ballSize)) {
            // ブロックの左上から衝突＝ボールの右下の点がブロック内
            return Direction.UP_LEFT;
        } else if (block.contains(ballX, ballY + ballSize)) {
            // ブロックの右上から衝突＝ボールの左下の点がブロック内
            return Direction.UP_RIGHT;
        }

        return Direction.NO_COLLISION;
    }


    public int getY() {
        return y;
    }
}

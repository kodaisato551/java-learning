import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainPanel extends JPanel implements Runnable,
        MouseMotionListener {
    private Racket racket;
    private Ball ball;
    private Thread game;
    public static final int WIDTH = 360;
    public static final int HEIGHT = 480;

    private static final int NUM_BLOCK_ROW = 10;
    private static final int NUM_BLOCK_COL = 7;
    private static final int NUM_BLOCK = NUM_BLOCK_ROW * NUM_BLOCK_COL;
    private Block[][] blocks = new Block[NUM_BLOCK_ROW][NUM_BLOCK_COL];

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseMotionListener(this);
        racket = new Racket();
        ball = new Ball();
        game = new Thread(this);
        createBlocks();
        game.start();
    }

    private void createBlocks() {
        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                int x = j * Block.WIDTH + Block.WIDTH;
                int y = i * Block.HEIGHT + Block.HEIGHT;
                blocks[i][j] = new Block(x, y);
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int x = e.getX(); // マウスのX座標
        racket.move(x); // ラケットを移動
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        racket.draw(g);
        ball.draw(g);

        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                if (!blocks[i][j].isDeleted()) {
                    blocks[i][j].draw(g);
                }
            }
        }
    }

    private void destroyBlock() {
        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                if (blocks[i][j].isDeleted()) {
                    continue;
                }
                Direction direction = blocks[i][j].collideWith(ball);
                if (direction.equals(Direction.NO_COLLISION)) {
                    break;
                } else {
                    blocks[i][j].delete();
                    reflection(direction);
                }
            }
        }
    }

    private void reflection(Direction direction) {
        switch (direction) {
            case DOWN, UP -> ball.boundY();
            case LEFT, RIGHT -> ball.boundX();
            case UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT -> ball.boundXY();
        }
    }

    @Override
    public void run() {
        while (true) {
            ball.move();
            if (racket.collideWith(ball)) {
                ball.boundY();
            }
            destroyBlock();
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

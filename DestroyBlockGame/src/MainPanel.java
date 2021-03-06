import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainPanel extends JPanel implements Runnable,
        MouseMotionListener {
    private Racket racket;
    private Ball ball;
    private Thread game;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 500;

    private static final int NUM_BLOCK_ROW = 15;
    private static final int NUM_BLOCK_COL = 7;
    private static final int NUM_BLOCK = NUM_BLOCK_COL * NUM_BLOCK_ROW;
    private Block[][] blocks = new Block[NUM_BLOCK_ROW][NUM_BLOCK_COL];

    private int blockBaseLineY;
    private int deletedBlock;

    public MainPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addMouseMotionListener(this);
        initGameFields();
        game = new Thread(this);
        game.start();
    }

    private void initGameFields(){
        createBlocks();
        racket = new Racket();
        ball = new Ball(blockBaseLineY);
    }

    private void createBlocks() {
        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                int x = j * Block.WIDTH + Block.WIDTH;
                int y = i * Block.HEIGHT + Block.HEIGHT;
                blocks[i][j] = new Block(x, y);
            }
        }
        blockBaseLineY = blocks[NUM_BLOCK_ROW - 1][0].getY();
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
        g.setColor(Color.LIGHT_GRAY);
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
        deletedBlock = 0;
        for (int i = 0; i < NUM_BLOCK_ROW; i++) {
            for (int j = 0; j < NUM_BLOCK_COL; j++) {
                if (blocks[i][j].isDeleted()) {
                    deletedBlock++;
                    continue;
                }
                Direction direction = blocks[i][j].collideWith(ball);
                if(!direction.equals(Direction.NO_COLLISION)){
                    blocks[i][j].delete();
                    deletedBlock++;
                    reflection(direction);
                    return;
                }
            }
        }
    }

    private void reflection(Direction direction) {
        switch (direction) {
            case DOWN:
            case UP:
                ball.boundY();
                break;
            case LEFT:
            case RIGHT:
                ball.boundX();
                break;
            case UP_LEFT:
            case UP_RIGHT:
            case DOWN_LEFT:
            case DOWN_RIGHT:
                ball.boundXY();
        }
    }

    @Override
    public void run() {
        while (true) {
            ball.move();
            if (ball.isGameOver()){
                popUpDialog("残念.");
            }
            if (racket.collideWith(ball)) {
                ball.boundY();
            }
            destroyBlock();
            if (deletedBlock == NUM_BLOCK){
                popUpDialog("成功.");
            }
            repaint();
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void popUpDialog(String message){
        int option = JOptionPane.showConfirmDialog(this,message + "ゲームを終了しますか？(Yes:終了/No:再挑戦)",
                "Finish",JOptionPane.YES_NO_OPTION);

        if (option == JOptionPane.YES_OPTION){
            System.exit(1);
        }else if (option == JOptionPane.NO_OPTION){
            initGameFields();
        }
    }
}

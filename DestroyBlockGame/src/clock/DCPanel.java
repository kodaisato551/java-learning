package clock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DCPanel extends JPanel {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    private Font font = new Font("Arial",Font.PLAIN,10);

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStringCenter(g, getCurrentTime());
    }

    /**
     * フレームの中央に文字列textを描画
     *
     * @param g    　グラフィック
     * @param text 　表示する文字列
     */
    private void drawStringCenter(Graphics g, String text) {
        FontMetrics fm = g.getFontMetrics(font);
        Dimension size = getSize();
        g.setColor(Color.BLACK);//BackGroundColor
        g.fillRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g.setColor(Color.WHITE);//FontColor
        g.setFont(font);
        int x = (size.width - fm.stringWidth(text)) / 2;
        int y = (size.height - fm.getHeight()) / 2 + fm.getMaxAscent();
        g.drawString(text, x, y);
    }

    /**
     * 現在の時刻をformatterのフォーマットに従って成型しStringを返す
     *
     * @return String
     */
    static String getCurrentTime() {
        return formatter.format(Calendar.getInstance().getTime());
    }
}

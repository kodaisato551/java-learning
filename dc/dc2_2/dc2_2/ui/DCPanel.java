package dc2_2;

import dc2_2.setting.DefaultSetting;
import dc2_2.util.Utils;

import javax.swing.*;
import java.awt.*;

public class DCPanel extends JPanel {

    private Font font = DefaultSetting.DEFAULT_FONT;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStringCenter(g, Utils.getCurrentTime());
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
        g.setColor(Color.WHITE);//BackGroundColor
        g.fillRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g.setColor(Color.BLACK);//FontColor
        g.setFont(font);
        int x = (size.width - fm.stringWidth(text)) / 2;
        int y = (size.height - fm.getHeight()) / 2 + fm.getMaxAscent();
        g.drawString(text, x, y);
    }

}

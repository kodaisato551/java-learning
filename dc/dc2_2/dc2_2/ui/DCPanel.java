package dc2_2.ui;

import dc2_2.setting.DefaultProperties;
import dc2_2.setting.Setting;
import dc2_2.util.Utils;

import javax.swing.*;
import java.awt.*;

public class DCPanel extends JPanel {

    private Font font = DefaultProperties.FONT;

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
        //Dimension size = Utils.calcFrameSize(fm);
        Dimension size = getSize();
        Dimension calSize = Utils.calcFrameSize(fm);
        System.out.println(size + " :: " + calSize);
        g.setColor(Setting.getInstance().getBgColor());//BackGroundColor
        g.fillRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g.setColor(Setting.getInstance().getFontColor());//FontColor
        g.setFont(Setting.getInstance().getCurrentFont());
        int x = (size.width - fm.stringWidth(text)) / 2;
        int y = (size.height - fm.getHeight()) / 2 + fm.getMaxAscent();
        g.drawString(text, x, y);
    }

}

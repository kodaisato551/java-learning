package dc2_3.ui;

import dc2_3.setting.DefaultProperties;
import dc2_3.setting.Setting;
import dc2_3.util.Utils;

import javax.swing.*;
import java.awt.*;

public class DCPanel extends JPanel {

    private DCFrame frame;
    private Setting setting = Setting.getInstance();


    DCPanel(DCFrame frame) {
        setSize(DefaultProperties.PANEL_WIDTH, DefaultProperties.PANEL_HEIGHT);
        this.frame = frame;
    }

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
        Dimension size = getSize();
        FontMetrics fm = getGraphics().getFontMetrics(setting.getCurrentFont());
        Dimension frameSize = Utils.calcFrameSize(fm);
        frame.setSize(frameSize);

        g.setColor(Setting.getInstance().getBgColor());//BackGroundColor
        g.fillRect(0, 0, getParent().getWidth(), getParent().getHeight());
        g.setColor(Setting.getInstance().getFontColor());//FontColor
        g.setFont(Setting.getInstance().getCurrentFont());
        int x = (size.width - fm.stringWidth(text)) / 2;
        int y = (size.height - fm.getHeight()) / 2 + fm.getMaxAscent();
        g.drawString(text, x, y);
    }

}

package dc2_3.ui;

import dc2_3.setting.DefaultProperties;
import dc2_3.setting.Setting;
import dc2_3.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DCPanel extends JPanel implements MouseListener {

    private DCWindow frame;
    private Setting setting = Setting.getInstance();

    // Menu
    private JPopupMenu popUpMenu;

    //Menu items
    private JMenuItem fontTypeMenuItem;
    private JMenuItem fontSizeMenuItem;
    private JMenuItem fontColorMenuItem;
    private JMenuItem bgColorMenuItem;
    private JMenuItem terminateMenuItem;

    DCPanel(DCWindow frame) {
        setSize(DefaultProperties.WINDOW_WIDTH, DefaultProperties.WINDOW_HEIGHT);
        this.frame = frame;
        initPopupMenu();
        setListeners();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawStringCenter(g, Utils.getCurrentTime());
    }

    private void initPopupMenu() {
        popUpMenu = new JPopupMenu();
        fontTypeMenuItem = addPopupMenuItem("Font type", null);
        fontSizeMenuItem = addPopupMenuItem("Font size", null);
        fontColorMenuItem = addPopupMenuItem("Font color", null);
        bgColorMenuItem = addPopupMenuItem("Background color", null);
        terminateMenuItem = addPopupMenuItem("Terminate", e -> System.exit(1));


    }

    private JMenuItem addPopupMenuItem(String name, ActionListener callBack) {
        JMenuItem item = new JMenuItem(name);
        if (callBack != null) {
            item.addActionListener(callBack);
        }
        popUpMenu.add(item);
        return item;
    }

    private void setListeners() {
        addMouseListener(this);
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            System.out.println("right button clicked");
            popUpMenu.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

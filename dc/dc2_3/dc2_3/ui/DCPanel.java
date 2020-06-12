package dc2_3.ui;

import dc2_3.setting.DefaultProperties;
import dc2_3.setting.Setting;
import dc2_3.setting.SupportedSettings;
import dc2_3.util.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class DCPanel extends JPanel {

    private DCWindow frame;
    private Setting setting = Setting.getInstance();

    // Menu
    private JPopupMenu popUpMenu;

    //Menu items
    private JMenu fontTypeMenu;
    private JMenu fontSizeMenu;
    private JMenu fontColorMenu;
    private JMenu bgColorMenu;
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
        fontTypeMenu = addPopupMenu("Font type");
        fontSizeMenu = addPopupMenu("Font size");
        fontColorMenu = addPopupMenu("Font color");
        bgColorMenu = addPopupMenu("Background color");
        terminateMenuItem = addPopupMenuItem(popUpMenu, "Terminate", e -> System.exit(1));

        addAllPopupMenuItem(fontTypeMenu, SupportedSettings.FONT_TYPE_LIST, e -> {
            Setting.getInstance().setFontType(e.getActionCommand());
        });

        addAllPopupMenuItem(fontSizeMenu, new ArrayList<>(SupportedSettings.FONT_SIZE_MAP.keySet()), e -> {
            Setting.getInstance().setFontSize(SupportedSettings.FONT_SIZE_MAP.get(e.getActionCommand()));
        });

        addAllPopupMenuItem(fontColorMenu, new ArrayList<>(SupportedSettings.SUPPORTED_COLOR.keySet()), e -> {
            Setting.getInstance().setFontColor(SupportedSettings.SUPPORTED_COLOR.get(e.getActionCommand()));
        });

        addAllPopupMenuItem(bgColorMenu, new ArrayList<>(SupportedSettings.SUPPORTED_COLOR.keySet()), e -> {
            Setting.getInstance().setBgColor(SupportedSettings.SUPPORTED_COLOR.get(e.getActionCommand()));
        });

    }

    private JMenu addPopupMenu(String name) {
        JMenu menu = new JMenu(name);
        popUpMenu.add(menu);
        return menu;
    }


    private JMenuItem addPopupMenuItem(JComponent parentMenu, String name, ActionListener callBack) {
        JMenuItem menu = new JMenuItem(name);
        if (callBack != null) {
            menu.addActionListener(callBack);
        }
        parentMenu.add(menu);
        return menu;
    }


    private void addAllPopupMenuItem(JComponent parentMenu, List<String> settingNames, ActionListener callBack) {
        for (String settingName : settingNames) {
            addPopupMenuItem(parentMenu, settingName, callBack);
        }
    }


    private void setListeners() {
        DragWindowListener listener = new DragWindowListener();
//        addMouseListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
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

    class DragWindowListener extends MouseAdapter {
        private final Point startPt = new Point();
        //private Point  loc;
        private Window window;


        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            if (SwingUtilities.isRightMouseButton(e)) {
                System.out.println("right button clicked");
                popUpMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        }

        @Override
        public void mousePressed(MouseEvent me) {
            startPt.setLocation(me.getPoint());
        }

        @Override
        public void mouseDragged(MouseEvent me) {
            if (window == null) {
                window = SwingUtilities.windowForComponent(me.getComponent());
            }
            Point eventLocationOnScreen = me.getLocationOnScreen();
            window.setLocation(eventLocationOnScreen.x - startPt.x,
                    eventLocationOnScreen.y - startPt.y);
        }
    }
}

package dc1_3;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.logging.Logger;

/**
 *・メニューをつけてプロパティダイアログを開ける
 *・プロパティダイアログ
 *　ーフォントの指定
 *　－フォントサイズの指定
 *　－文字色の指定
 *　－時計の背景色の指定
 *
*ちらつきをなくすようにダブルバッファリングする
 *・フォントとフォントサイズを変更すると、時計を表示すべきフレームの大きさを適切に自動変更して正しく表示されるようにする。
 *・FrameではなくWindowクラスを使用して、フレーム枠のないデジタル時計にする
 *・マウスの右クリックでポップアップメニューを表示してカスケード形式で選択できるようにする
 *・ 左クリックしたままデスクトップ上でウインドウを移動させることができる
 *
 *TODO M:設定を変更する箇所をダイアログ画面ではなく、メニューにする
 *
 *
 * @author Sato Kodai
 */
public class DCFrame extends Window implements ActionListener, MouseMotionListener, MouseListener {

	private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	private int width;
	private int height;
	Logger logger = Logger.getLogger(this.getClass().getName());
	SettingItem settingItem = SettingItem.getInstance();

	private Font font;
	private PopupMenu menu;
	private PropertyDialog propertyDialog;
	private final Point startPt = new Point();

	public DCFrame(Frame frame) {
		super(frame);
		setVisible(true);
		defineFrameParam();
		setSize(width, height);

		//dialogの設定
		propertyDialog = new PropertyDialog(frame);
		propertyDialog.setVisible(false);
		initPopUpMenu();
		this.add(menu);

		//右クリックでポップアップを表示
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	private void initPopUpMenu() {
		menu = new PopupMenu();
		//fontTypeMenuの設定
		String[] fontType = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		initMenuInflater("Font Type", Arrays.asList(fontType), e -> {
			settingItem.setFontType(e.getActionCommand());
		});
		//fontColorの設定
		initMenuInflater("Font Color", SupportedColor.getKeys(), e -> {
			settingItem.setFontColor(e.getActionCommand());
		});
		//fontsizeの設定
		initMenuInflater("Font Size", FontSize.getKeys(), e -> {
			settingItem.setFontSize(e.getActionCommand());
		});
		//BackGroundColorの設定
		initMenuInflater("Background Color", SupportedColor.getKeys(), e -> {
			settingItem.setBackGroundColor(e.getActionCommand());
		});

		//terminate
		MenuItem mi = new MenuItem("Terminate");
		mi.addActionListener(e -> {
			System.exit(0);
		});
		menu.add(mi);
	}

	/*
	 * menu生成に関する共通処理をここに記載する.
	 * menuに対し設定の大項目を追加するまでに至る過程を記述する
	 * @param 設定値の大項目名前
	 * @param 具体的な設定値のcollection、各要素はstring型
	 * @param 各メニューアイテムに対し行いたい処理
	 */
	private void initMenuInflater(String menuName, Collection<String> settingParam, ActionListener l) {
		Menu settingMenu = new Menu(menuName);
		MenuItem[] menuItems = new MenuItem[settingParam.size()];
		int count = 0;
		for (String item : settingParam) {
			menuItems[count] = new MenuItem(item);
			menuItems[count].addActionListener(l);
			settingMenu.add(menuItems[count]);
			count++;
		}
		menu.add(settingMenu);
	}

	/**
	 * 時計の処理
	 */
	public void run() {
		while (true) {
			repaint();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void paint(Graphics g) {
		drawStringCenter(g, getCurrentTime(), width / 2, height / 2);
	}

	/**
	 * フレームパラメータの設定・変更
	 * フォントサイズなどの変更に伴い、フレームのサイズも変更する。
	 * @return
	 */
	private void defineFrameParam() {
		font = new Font(settingItem.getFontType(), Font.BOLD, settingItem.getFontSize());
		FontMetrics fm = getGraphics().getFontMetrics(font);
		height = fm.getHeight() * ConstSettingParam.getPropHeight();
		width = fm.stringWidth("00:00:00") * ConstSettingParam.getPropWidth();
		setSize(width, height);
	}

	/**
	 * フレームの中央に文字列textを描画します
	 * @param g
	 * @param text
	 * @param x
	 * @param y
	 */
	private void drawStringCenter(Graphics g, String text, int x, int y) {
		defineFrameParam();
		Image back = createImage(getWidth(), getHeight());
		Graphics buffer = back.getGraphics();
		buffer.setColor(settingItem.getBackGroundColor());
		buffer.fillRect(0, 0, getWidth(), getHeight());
		buffer.setFont(font);
		buffer.setColor(settingItem.getFontColor());
		FontMetrics fm = buffer.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(text, buffer).getBounds();
		x = x - rectText.width / 2;
		y = y - rectText.height / 2 + fm.getMaxAscent();
		buffer.drawString(text, x, y);
		g.drawImage(back, 0, 0, this);
		super.paint(g);
	}

	/**
	 * 現在の時刻をformatterのフォーマットに従って成型しStringを返す
	 * @return String
	 */
	private String getCurrentTime() {
		return formatter.format(Calendar.getInstance().getTime());
	}

	public static void main(String[] args) {
		new DCFrame(new Frame()).run();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			menu.show(this, e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		startPt.setLocation(e.getPoint());
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

	@Override
	public void mouseDragged(MouseEvent e) {
		Point point = e.getLocationOnScreen();
		setLocation(point.x - startPt.x, point.y - startPt.y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.print("Mouse point[x=" + e.getPoint().x + ", y=" + e.getPoint().y + "]  ");
		System.out.println("Absolute point[x=" + e.getLocationOnScreen().x + ",y=" + e.getLocationOnScreen().y + "] ");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}

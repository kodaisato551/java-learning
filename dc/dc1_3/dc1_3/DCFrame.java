package dc1_3;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
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
import java.util.Calendar;
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
 *TODO w:プロパティダイヤログを表示する際にwindowがバックグラウンドになる
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

	private ActionListener popupItemListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand();
			logger.info("menue event");
			if (command.equals("font setting")) {
				propertyDialog.setVisible(true);
			} else if (command.equals("terminate")) {
				logger.info("terminate");
				System.exit(0);
			}
		}
	};

	private int windowX = 0;
	private int windowY = 0;

	private final Point startPoint = new Point();

	public DCFrame(Frame frame) {
		super(frame);
		setVisible(true);
		defineFrameParam();
		setSize(width, height);
		//addWindowCloseEvent();

		//dialogの設定
		propertyDialog = new PropertyDialog(frame);
		propertyDialog.setVisible(false);
		initPopupMenu();
		this.add(menu);

		//右クリックでポップアップを表示
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	private void initPopupMenu() {
		menu = new PopupMenu();
		MenuItem item = new MenuItem("font setting");
		MenuItem item2 = new MenuItem("terminate");
		item.addActionListener(popupItemListener);
		item2.addActionListener(popupItemListener);
		menu.add(item);
		menu.add(item2);
	}

	/**
	 * windowがCloseするイベントを登録する
	 */
	//	private void addWindowCloseEvent() {
	//		addWindowListener(new WindowAdapter() {
	//			@Override
	//			public void windowClosing(WindowEvent e) {
	//				System.exit(0);
	//			}
	//		});
	//	}

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
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point eventLocation = e.getLocationOnScreen();
		windowX = eventLocation.x - startPoint.x;
		windowY = eventLocation.y - startPoint.y;
		setLocation(windowX, windowY);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}

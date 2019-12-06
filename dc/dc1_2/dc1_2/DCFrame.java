package dc1_2;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
 *・ちらつきをなくすようにダブルバッファリングする
 *・フォントとフォントサイズを変更すると、時計を表示すべきフレームの大きさを適切に自動変更して正しく表示されるようにする。
 *
 *TODO w:ちゃんと中央に表示されるようにする
 *TODO M:設定項目に説明を加える
 *
 * @author Sato Kodai
 */
public class DCFrame extends Frame implements ActionListener, SettingChangeNotifier {
	private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	private int width;
	private int height;
	Logger logger = Logger.getLogger(this.getClass().getName());
	SettingItem settingItem = SettingItem.getInstance();

	private Font font;

	private PropertyDialog propertyDialog;

	public DCFrame(String title) {
		super(title);
		setVisible(true);
		defineFrameParam();
		setSize(width, height);
		addWindowCloseEvent();

		/* dialog の設定*/
		propertyDialog = new PropertyDialog(this, this);
		propertyDialog.setSize(500, 600);
		propertyDialog.setVisible(false);
		setMenuBar(createMenuBar());
	}

	/**
	 * menuebarの作成
	 * @return
	 */
	private MenuBar createMenuBar() {
		MenuBar mb = new MenuBar();
		Menu menu = new Menu("setting");
		MenuItem item = new MenuItem("general");

		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.info("menue event");
				propertyDialog.setVisible(true);
			}
		});
		menu.add(item);
		mb.add(menu);

		return mb;
	}

	/**
	 * windowがCloseするイベントを登録する
	 */
	private void addWindowCloseEvent() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
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
		new DCFrame("DC").run();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (propertyDialog == null) {
			propertyDialog = new PropertyDialog(this, this);
		}
	}

	@Override
	public void notifySettingChanged() {
		//		getGraphics().setFont(new Font("Arial", Font.BOLD, settingItem.getFontSize()));
	}
}

package dc1_4;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;
import java.util.prefs.Preferences;

/**
 *・レイアウトマネージャは、GridBagLayout を使用する
 *・プロパティダイアログは、「属性名」との「値の選択リスト」が縦に並ぶようにする。
	フォント フォントの値の選択リスト
	フォントサイズ サイズの値の選択リスト
	文字色 色の値の選択リスト
	背景色 色の値の選択リスト
	この場合「属性名」のラベルは右寄せして、「値の選択リスト」メニューは左寄せにする。
	・ダイアログの下には、「OK」「キャンセル」のボタンをダイアログの右下に寄せて表示し、
	それぞれのボタンを実装する。キャンセルされた場合には、正しく、元の値に戻るようにする。

	・ java.util.prefs パッケージを使用して、プロパティダイアログの内容の保存と、時計の終了時の位置を保存する。
	再度、時計を起動した場合に、それらの保存を復元して、デスクトップの元の位置に表示されるようにする。
 */
public class DCFrame extends Frame implements ActionListener, SettingChangeNotifier {
	private final SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	private int width;
	private int height;
	Logger logger = Logger.getLogger(this.getClass().getName());
	SettingItem settingItem = SettingItem.getInstance();
	private Font font;
	private PropertyDialog propertyDialog;

	private final Preferences pref = Preferences.userRoot().node("digital_clock");

	//prefの保存
	private void savePrefs() {
		settingItem.getFiled();
		pref.put(ConstSettingParam.FONT_TYPE_NAME, settingItem.getFontType());
		pref.put(ConstSettingParam.FONT_SIZE_NAME, settingItem.getFontSizeString());
		pref.put(ConstSettingParam.FONT_COLOR_NAME, settingItem.getFontColorString());
		pref.put(ConstSettingParam.BACKGROUND_COLOR_NAME, settingItem.getBackGroundColorString());
		Point point = getLocation();
		pref.putInt(ConstSettingParam.START_POINT_X, point.x);
		pref.putInt(ConstSettingParam.START_POINT_Y, point.y);
		//		try {
		//			pref.flush();
		//		} catch (BackingStoreException e) {
		//			e.printStackTrace();
		//		}
	}

	//perfのload
	private void loadPrefs() {
		System.out.println("before");
		settingItem.getFiled();
		settingItem.setFontSize(pref.get(ConstSettingParam.FONT_SIZE_NAME, FontSize.MEDIUM.getName()));
		settingItem.setFontType(pref.get(ConstSettingParam.FONT_TYPE_NAME, ConstSettingParam.getFontTypeList().get(0)));
		settingItem.setFontColor(pref.get(ConstSettingParam.FONT_COLOR_NAME, SupportedColor.BLACK.getName()));
		settingItem
				.setBackGroundColor(pref.get(ConstSettingParam.BACKGROUND_COLOR_NAME, SupportedColor.WHITE.getName()));
		int x = pref.getInt(ConstSettingParam.START_POINT_X, 0);
		int y = pref.getInt(ConstSettingParam.START_POINT_Y, 0);
		setLocation(x, y);
		System.out.println(pref);
		System.out.println("after");
		settingItem.getFiled();
	}

	public DCFrame(String title) {
		super(title);

		setVisible(true);

		setSize(width, height);
		addWindowCloseEvent();

		/* dialog の設定*/
		propertyDialog = new PropertyDialog(this, this);
		propertyDialog.setVisible(false);
		setMenuBar(createMenuBar());
		defineFrameParam();
		loadPrefs();

		settingItem.getFiled();
	}

	//メニューバーの生成
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
				savePrefs();
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

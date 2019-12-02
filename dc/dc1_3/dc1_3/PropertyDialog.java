package dc1_3;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PropertyDialog extends Dialog implements ActionListener {

	private int width;
	private int height;

	SettingChangeNotifier notifier;

	private SettingItem settingItem = SettingItem.getInstance();

	private Choice fontSizeChoice;
	private Choice fontTypeChoice;
	private Choice fontColorChoice;
	private Choice backGroundColorChoice;
	private Button button;

	public PropertyDialog(Frame owner, SettingChangeNotifier notifier) {
		this(owner, 500, 600, notifier);

	}

	public PropertyDialog(Frame owner, int width, int height, SettingChangeNotifier notifier) {
		super(owner);
		setSize(width, height);
		this.notifier = notifier;
		addWindowListener(new DialogWindowListener());
		setFont(new Font("Arial", Font.BOLD, 20));

		Label l1 = new Label("Font Size:");
		add(l1);
		fontSizeChoice = new Choice();
		for (String string : FontSize.getKeys()) {
			fontSizeChoice.add(string);
		}
		add(fontSizeChoice);

		Label l2 = new Label("Font Type:");
		add(l2);
		fontTypeChoice = new Choice();
		for (String string : ConstSettingParam.getFontTypeList()) {
			fontTypeChoice.add(string);
		}
		add(fontTypeChoice);

		Label l3 = new Label("Font Color:");
		add(l3);
		fontColorChoice = new Choice();
		for (String string : SupportedColor.getKeys()) {
			fontColorChoice.add(string);
		}
		add(fontColorChoice);

		Label l4 = new Label("Back Ground Color:");
		add(l4);
		backGroundColorChoice = new Choice();
		for (String string : SupportedColor.getKeys()) {
			backGroundColorChoice.add(string);
		}
		add(backGroundColorChoice);

		setLayout(new GridLayout(8, 2));
		setSize(350, 250);

		//設定完了ボタン
		button = new Button("setting done");
		button.addActionListener(this);
		add(button);
	}

	public void changeWidth(int width) {
		this.width = width;
		setSize(width, height);
	}

	public void changeHeight(int height) {
		this.height = height;
		setSize(width, height);
	}

	class DialogWindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			setVisible(false);
		}
	}

	/**
	 * setting done押下時の処理
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		settingItem.setFontSize(fontSizeChoice.getSelectedItem());
		settingItem.setFontType(fontTypeChoice.getSelectedItem());
		settingItem.setFontColor(fontColorChoice.getSelectedItem());
		settingItem.setBackGroundColor(backGroundColorChoice.getSelectedItem());

		notifier.notifySettingChanged();
		setVisible(false);
	}

}

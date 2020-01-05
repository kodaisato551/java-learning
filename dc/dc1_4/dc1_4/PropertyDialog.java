package dc1_4;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.Window;
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
	private Button okButton;
	private Button cancelButton;

	public PropertyDialog(Window dcFrame, SettingChangeNotifier notifier) {
		this(dcFrame, 500, 200, notifier);

	}

	public PropertyDialog(Window owner, int width, int height, SettingChangeNotifier notifier) {
		super(owner);
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		setResizable(false);
		setSize(width, height);
		this.notifier = notifier;
		addWindowListener(new DialogWindowListener());
		setFont(new Font("Arial", Font.CENTER_BASELINE, 20));

		Label l1 = new Label(ConstSettingParam.FONT_SIZE_NAME);
		fontSizeChoice = new Choice();
		for (String string : FontSize.getKeys()) {
			fontSizeChoice.add(string);
		}

		Label l2 = new Label(ConstSettingParam.FONT_TYPE_NAME);
		fontTypeChoice = new Choice();
		for (String string : ConstSettingParam.getFontTypeList()) {
			fontTypeChoice.add(string);
		}

		Label l3 = new Label(ConstSettingParam.FONT_COLOR_NAME);
		fontColorChoice = new Choice();
		for (String string : SupportedColor.getKeys()) {
			fontColorChoice.add(string);
		}

		Label l4 = new Label(ConstSettingParam.BACKGROUND_COLOR_NAME);
		backGroundColorChoice = new Choice();
		for (String string : SupportedColor.getKeys()) {
			backGroundColorChoice.add(string);
		}

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 0;
		layout.setConstraints(l1, gbc);
		add(l1);

		gbc.gridx = 1;
		gbc.gridy = 0;
		layout.setConstraints(fontSizeChoice, gbc);
		add(fontSizeChoice);

		gbc.gridx = 0;
		gbc.gridy = 1;
		layout.setConstraints(l2, gbc);
		add(l2);

		gbc.gridx = 1;
		gbc.gridy = 1;
		layout.setConstraints(fontTypeChoice, gbc);
		add(fontTypeChoice);

		gbc.gridx = 0;
		gbc.gridy = 2;
		layout.setConstraints(l3, gbc);
		add(l3);

		gbc.gridx = 1;
		gbc.gridy = 2;
		layout.setConstraints(fontColorChoice, gbc);
		add(fontColorChoice);

		gbc.gridx = 0;
		gbc.gridy = 3;
		layout.setConstraints(l4, gbc);
		add(l4);

		gbc.gridx = 1;
		gbc.gridy = 3;
		layout.setConstraints(backGroundColorChoice, gbc);
		add(backGroundColorChoice);

		okButton = new Button("OK");
		okButton.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 4;
		layout.setConstraints(okButton, gbc);
		add(okButton);

		cancelButton = new Button("Cancel");
		cancelButton.addActionListener(this);
		gbc.gridx = 1;
		gbc.gridy = 4;
		layout.setConstraints(cancelButton, gbc);
		add(cancelButton);
		//		//設定完了ボタン
		//		button = new Button("setting done");
		//		button.addActionListener(this);
		//		add(button);
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
		switch (e.getActionCommand()) {
		case "OK":
			settingChange();
			break;
		default:
			setVisible(false);
			break;
		}

	}

	private void settingChange() {
		settingItem.setFontSize(fontSizeChoice.getSelectedItem());
		settingItem.setFontType(fontTypeChoice.getSelectedItem());
		settingItem.setFontColor(fontColorChoice.getSelectedItem());
		settingItem.setBackGroundColor(backGroundColorChoice.getSelectedItem());
		notifier.notifySettingChanged();
		setVisible(false);
	}

}

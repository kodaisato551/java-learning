package dc2_2.ui;

import dc2_2.setting.DefaultProperties;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class PropertyDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/* font color */
	private JLabel lblFontColorName;
	private JPanel colorPanel;
	private JColorChooser fontColorChooser;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PropertyDialog dialog = new PropertyDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PropertyDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			JPanel fontTypePanel = new JPanel();
			contentPanel.add(fontTypePanel);
			fontTypePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblNewLabel = new JLabel("Font Type :");
				fontTypePanel.add(lblNewLabel);
			}
			{
				JComboBox comboBox = new JComboBox();
				fontTypePanel.add(comboBox);
			}
		}
		{
			JPanel fontSizePanel = new JPanel();
			contentPanel.add(fontSizePanel);
			fontSizePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblFontSize = new JLabel("Font Size :");
				fontSizePanel.add(lblFontSize);
			}
			{
				JSpinner spinner = new JSpinner();
				fontSizePanel.add(spinner);
			}
		}
		{
			JPanel fontColorPanel = new JPanel();
			contentPanel.add(fontColorPanel);
			fontColorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblFontColor = new JLabel("Font Color : ");
				fontColorPanel.add(lblFontColor);
			}
			{
				JPanel panel = new JPanel();
				fontColorPanel.add(panel);
				{
					lblFontColorName = new JLabel("Black");
					panel.add(lblFontColorName);
				}
				{
					JPanel colorPanel = new JPanel();
					colorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
					panel.add(colorPanel);
				}
				{
					JButton btnGoToColorChooser = new JButton("Go to color chooser");
					btnGoToColorChooser.addActionListener(
							e -> {
								fontColorChooser = new JColorChooser(DefaultProperties.DEFAULT_FONT_COLOR);
								fontColorChooser.setVisible(true);
							}
					);
					panel.add(btnGoToColorChooser);
				}
			}
		}
		{
			JPanel backgroundColorPanel = new JPanel();
			contentPanel.add(backgroundColorPanel);
			backgroundColorPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JLabel lblBackgroundColor = new JLabel("Background Color : ");
				backgroundColorPanel.add(lblBackgroundColor);
			}
			{
				JPanel panel = new JPanel();
				backgroundColorPanel.add(panel);
				{
					JComboBox comboBox = new JComboBox();
					panel.add(comboBox);
				}
				{
					JPanel bgColorPanel = new JPanel();
					bgColorPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
					panel.add(bgColorPanel);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

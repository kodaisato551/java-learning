package interpret.ui;

import interpret.data.ObjectPool;
import interpret.util.LexicalAnalyzer;
import interpret.util.ReflectUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ModifyFieldDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JButton okButton;
	private JComboBox comboBox;
	private JButton cancelButton;

	private InvokeUIFrame parentFrame;

	private Object targetObject;
	private Field field;
	private int fieldIndex;

	private List<Object> objectList;

	private final ActionListener OK_BUTTON = (e) -> {
		Object updatedValue = null;


		if (field.getType().isPrimitive()) {
			try {
				List<Object> objs = new ArrayList<>();
				LexicalAnalyzer.createPrimitiveInstanceFromStringValue(field.getType(), textField.getText(), objs);
				updatedValue = objs.get(0);

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, ex.getMessage());
			}
		} else {
			updatedValue = objectList.get(comboBox.getSelectedIndex());
		}
		if (updatedValue == null) {
			JOptionPane.showMessageDialog(this, "update value not set or illegal. try again.");
			return;
		}
		try {
			System.out.println("Dialog::okButton invoke FIELD:" + ObjectPool.getInstance().getDisplayName(field));
			System.out.println("Dialog::okButton invoke TARGETOBJ:" + ObjectPool.getInstance().getDisplayName(targetObject));

			ReflectUtil.setField(targetObject, field, updatedValue);
		} catch (Throwable throwable) {
			JOptionPane.showMessageDialog(this, throwable.getMessage());
		}
		parentFrame.updateFieldsAt(targetObject, fieldIndex);
		this.dispose();
	};


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Object obj = "sato";
			ModifyFieldDialog dialog = new ModifyFieldDialog(obj, 0, new InvokeUIFrame(obj));
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}
	}

	private void init(Object targetObject, int fieldIndex, InvokeUIFrame parentFrame) {
		this.targetObject = targetObject;
		this.fieldIndex = fieldIndex;
		this.field = targetObject.getClass().getDeclaredFields()[fieldIndex];
		Class<?> clazz = field.getType();
		objectList = ObjectPool.getInstance().grep(clazz);
		if (clazz.isPrimitive()) {
			JOptionPane.showMessageDialog(this, " this filed type is primitive. please input text filed");
		}
		this.parentFrame = parentFrame;
	}


	/**
	 * Create the dialog.
	 */
	public ModifyFieldDialog(Object targetObject, int fieldIndex, InvokeUIFrame parentFrame) {
		init(targetObject, fieldIndex, parentFrame);


		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			comboBox = new JComboBox();
			for (Object obj : objectList) {
				comboBox.addItem(ObjectPool.getInstance().getDisplayName(obj));
			}
			contentPanel.add(comboBox);
			//プリミティブの場合非活性にする
			if (field.getType().isPrimitive()) {
				comboBox.setEnabled(false);
			}

		}
		{
			textField = new JTextField();
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setListeners();
	}


	private void setListeners() {
		okButton.addActionListener(OK_BUTTON);
		cancelButton.addActionListener(e -> {
			this.dispose();
		});
	}


}

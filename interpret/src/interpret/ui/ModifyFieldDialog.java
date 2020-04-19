package interpret.ui;

import interpret.data.ObjectPool;
import interpret.util.ReflectUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
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
		int index = comboBox.getSelectedIndex();
		Object obj = objectList.get(index);
		try {
			ReflectUtil.setField(targetObject, field, obj);
		} catch (Throwable throwable) {
			JOptionPane.showMessageDialog(this, throwable.getMessage());
		}
		parentFrame.updateFieldsAt(targetObject, fieldIndex);

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
		objectList = ObjectPool.getInstance().grep(targetObject.getClass());
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

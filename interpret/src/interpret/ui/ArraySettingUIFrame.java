package interpret.ui;

import interpret.data.ObjectPool;
import interpret.setting.Consts;
import interpret.ui.table.ArrayJTable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO ここでインスタンスを生成したら、メインのフレームでのリストを表示させないといけない。メインに何らかの通知を行う？
 */
public class ArraySettingUIFrame extends JFrame {
	SpinnerNumberModel spinnerNumberModel;
	JSpinner inputArraySize;
	JButton btnDecideArraySize;
	JButton btnCreateArrayInstance;
	private JPanel contentPane;
	private CreateInstanceUIFrame parentFrame;

	private Class<?> clazz = null;
	private int arraySize = 0;
	private List<Object> objectList;
	private List<JTextField> textFields = new ArrayList<>();
	private List<JComboBox<String>> comboBoxList = new ArrayList<>();

	private static final String HEADER_TITLE[] = { "Index Num", "Select from created object", "Input if primitive" };
	private DefaultTableModel defaultTableModel = new DefaultTableModel(HEADER_TITLE, 3);
	private MutableComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();
	private List<MutableComboBoxModel<String>> comboBoxModelList = new ArrayList<>();


	private final ActionListener DECIDE_SIZE = e -> {
		clearTable();
		arraySize = (Integer) spinnerNumberModel.getNumber();
		defaultTableModel.setRowCount(arraySize);
		configTable(arraySize);
	};

	private final ActionListener CREATE_ARRAY = e -> {
		if (arraySize == 0) {
			JOptionPane.showMessageDialog(this, "Array size must be >0");
		} else {
			Object array = Array.newInstance(clazz, arraySize);
			boolean containNullFromArray = false;
			for (int i = 0; i < arraySize; i++) {
				int selectedIndex = comboBoxList.get(i).getSelectedIndex();
				System.out.println("item selected index : " + selectedIndex);
				if (selectedIndex == 0) {

					containNullFromArray = true;
				} else {
					Object obj = ObjectPool.getInstance().get(selectedIndex - 1);
					Array.set(array, i, obj);
				}
			}
			if (containNullFromArray) {
				JOptionPane.showMessageDialog(this, "Null is put in the corresponding element because the element of the array is not selected");
			}
			ObjectPool.getInstance().add(array);
			System.out.println(ObjectPool.getInstance().getAll());
			/*
			ここに何かをフレームに通知
			 */

			if (parentFrame == null) {
				System.out.println("frame is null");
			}
			if (parentFrame.getInstanceModel() == null) {
				System.out.println("model is null");
			}
			parentFrame.getInstanceModel().addElement(ObjectPool.getInstance().getDisplayName(array));
		}
	};


	/**
	 * Create the frame.
	 */
	public ArraySettingUIFrame(Class<?> clazz, CreateInstanceUIFrame parent) {
		inicialize(clazz, parent);
		setLayout();
		setListener();
	}

	/**
	 * フィールドの初期化を行います
	 */
	private void inicialize(Class<?> clazz, CreateInstanceUIFrame parentFrame) {
		this.parentFrame = parentFrame;
		spinnerNumberModel = new SpinnerNumberModel(0, null, null, 1);
		this.clazz = clazz;
		objectList = ObjectPool.getInstance().grep(clazz);
		System.out.println("grepped objList size : " + objectList.size());
	}

	private void setListener() {
		btnDecideArraySize.addActionListener(DECIDE_SIZE);
		btnCreateArrayInstance.addActionListener(CREATE_ARRAY);
	}

//	/**
//	 * Launch the application.(for test)
//	 */
//	public static void main(String[] args) {
//
//		ObjectPool.getInstance().add("sato");
//		ObjectPool.getInstance().add("yamada");
//
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ArraySettingUIFrame frame = new ArraySettingUIFrame(String.class, new CreateInstanceUIFrame());
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * 配列のサイズを受け次第。tableの設定をする.
	 *
	 * @param size
	 */
	private void configTable(int size) {

		for (int i = 0; i < size; i++) {

			MutableComboBoxModel<String> model = new DefaultComboBoxModel<>();
			model.addElement("not selected");
			for (int j = 0; j < objectList.size(); j++) {
				Object tmp = objectList.get(j);
				model.addElement(ObjectPool.getInstance().getDisplayName(tmp));
			}
			comboBoxModelList.add(model);
			comboBoxList.add(new JComboBox<>(model));
			textFields.add(new JTextField());
			defaultTableModel.setValueAt(new JLabel(String.valueOf(i)), i, 0);
			defaultTableModel.setValueAt(comboBoxList.get(i), i, 1);
			defaultTableModel.setValueAt(textFields.get(i), i, 2);
		}


	}


	private void clearTable() {
		while (defaultComboBoxModel.getSize() > 0) {
			defaultComboBoxModel.removeElementAt(0);
		}
		comboBoxModelList.clear();
		textFields.clear();
	}

	private void setLayout() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(Consts.MAIN_FRAME_SIZE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel header_panel = new JPanel();
		contentPane.add(header_panel, BorderLayout.NORTH);
		header_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JLabel lblNewLabel = new JLabel("Class Name : " + clazz.getCanonicalName());
		lblNewLabel.setSize(100, 30);
		header_panel.add(lblNewLabel);

		JPanel arraySize_panel = new JPanel();
		header_panel.add(arraySize_panel);
		arraySize_panel.setLayout(new GridLayout(1, 0, 0, 0));

		JSpinner inputArraySize = new JSpinner(spinnerNumberModel);
		arraySize_panel.add(inputArraySize);

		btnDecideArraySize = new JButton("Decide Size");
		arraySize_panel.add(btnDecideArraySize);

		JPanel content_panel = new JPanel();
		contentPane.add(content_panel, BorderLayout.CENTER);

		ArrayJTable table = new ArrayJTable(defaultTableModel);
		JScrollPane jScrollPane = new JScrollPane(table);
		JPanel jPanel = new JPanel();
		jPanel.add(jScrollPane);

		content_panel.add(jPanel, BorderLayout.CENTER);

		JPanel futtre_panel = new JPanel();
		contentPane.add(futtre_panel, BorderLayout.SOUTH);

		btnCreateArrayInstance = new JButton("Create array instance");
		futtre_panel.add(btnCreateArrayInstance);

	}
}

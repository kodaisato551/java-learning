package interpret.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.MutableComboBoxModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import interpret.data.ObjectPool;
import interpret.setting.Consts;
import interpret.ui.table.ArrayJTable;

public class ArraySettingUIFrame extends JFrame {
	SpinnerNumberModel spinnerNumberModel;
	JSpinner inputArraySize;
	JButton btnDecideArraySize;

	private Class<?> clazz = null;
	private int arraySize = 0;
	private List<Object> objectList;
	private List<JTextField> textFields = new ArrayList<>();
	private List<JComboBox<String>> comboBoxList = new ArrayList<>();

	private static final String HEADER_TITLE[] = { "Index Num", "Select from created object", "Input if primitive" };
	private JPanel contentPane;
	private DefaultTableModel defaultTableModel = new DefaultTableModel(HEADER_TITLE, 3);
	private MutableComboBoxModel<String> defaultComboBoxModel = new DefaultComboBoxModel<>();
	private JComboBox<String> comboBox = new JComboBox<String>(defaultComboBoxModel);
	private JPanel comboBoxPanel = new JPanel();

	private final ActionListener DECIDE_SIZE = e -> {
		arraySize = (Integer) spinnerNumberModel.getNumber();
		defaultTableModel.setRowCount(arraySize);
		configTable(arraySize);
	};

	private final ActionListener CREATE_ARRAY = e -> {
		if (arraySize == 0) {
			JOptionPane.showMessageDialog(this, "Array size must be >0");
		} else {

		}
	};

	/**
	 * Create the frame.
	 */
	public ArraySettingUIFrame(Class<?> clazz) {
		inicialize(clazz);
		setLayout();
		setListener();
	}

	/**
	 * フィールドの初期化を行います
	 */
	private void inicialize(Class<?> clazz) {
		spinnerNumberModel = new SpinnerNumberModel(0, null, null, 1);
		this.clazz = clazz;
		objectList = ObjectPool.getInstance().grep(clazz);
		System.out.println("grepped objList size : " + objectList.size());
	}

	private void setListener() {
		btnDecideArraySize.addActionListener(DECIDE_SIZE);
	}

	/**
	 * Launch the application.(for test)
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			ObjectPool.getInstance().add(new String());
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArraySettingUIFrame frame = new ArraySettingUIFrame(String.class);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 配列のサイズを受け次第。tableの設定をする.
	 *
	 * @param size
	 */
	private void configTable(int size) {
		defaultComboBoxModel.addElement("not selected");
		for (int i = 0; i < objectList.size(); i++) {
			Object tmp = objectList.get(i);
			defaultComboBoxModel.addElement(tmp.getClass().getSimpleName() + "#" + tmp.hashCode());
		}

		System.out.println("BoxModel : " + defaultComboBoxModel.getSize());
		for (int i = 0; i < size; i++) {
			textFields.add(new JTextField());
			comboBoxList.add(new JComboBox<>(defaultComboBoxModel));
			defaultTableModel.setValueAt(new JLabel(String.valueOf(i)), i, 0);
			defaultTableModel.setValueAt(comboBoxList.get(i), i, 1);
			defaultTableModel.setValueAt(textFields.get(i), i, 2);
		}

	}

	private void setLayout() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		comboBoxPanel.add(comboBox);

		ArrayJTable table = new ArrayJTable(defaultTableModel);
		JScrollPane jScrollPane = new JScrollPane(table);
		JPanel jPanel = new JPanel();
		jPanel.add(jScrollPane);

		content_panel.add(jPanel, BorderLayout.CENTER);

		JPanel futtre_panel = new JPanel();
		contentPane.add(futtre_panel, BorderLayout.SOUTH);

		JButton btnCreateArrayInstance = new JButton("Create array instance");
		futtre_panel.add(btnCreateArrayInstance);

	}
}

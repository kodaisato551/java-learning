package interpret.ui;

import interpret.data.ObjectPool;
import interpret.setting.Consts;
import interpret.util.LexicalAnalyzer;
import interpret.util.ReflectUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO エラーも表示する
 * TODO アレイ入力も対応する
 * TODO メソッドの検索
 */
class InvokeUIFrame extends JFrame {

	private static final long serialVersionUID = -5188095995143316568L;
	private final int objectPoolIndex;//オブジェクトが指し示す
	private final DefaultListModel mothodsModel = new DefaultListModel();
	private final DefaultListModel fieldModel = new DefaultListModel();
	private final List<JTextField> inputParams = new ArrayList<>();
	private JPanel contentPane;
	private JList methodJList;
	private GridLayout layout;
	private JPanel methodParamListPanel;
	private JButton btnInvoke;
	private JList fieldJList;
	private JTextField textField;
	private Object object;
	private Method[] methods;
	private Field[] fields;
	private List<String> paramList;
	private JButton btnSetField;
	private List<Object> fieldObjectList = new ArrayList<>();



	/**
	 *
	 */
	private final ListSelectionListener GET_METHOD_PARAM = e -> {
		if (e.getValueIsAdjusting()) {
			return;
		}

		Method met = methods[methodJList.getSelectedIndex()];
		paramList = LexicalAnalyzer.findParams(met.toString());
		deleteComponentFromPanel(methodParamListPanel);
		setCompToParamPanel(methodParamListPanel, paramList);
	};
	private final ActionListener INVOKE = (e) -> {
		try {
			Object returnValue = ReflectUtil.invoke(object, methods[methodJList.getSelectedIndex()],
					LexicalAnalyzer.parse(paramList, inputParams));
			if (returnValue == null) {
				//JOptionPane.showMessageDialog(this, "void");
			} else {
				JOptionPane.showMessageDialog(this, String.valueOf(returnValue));
			}
		} catch (Throwable t) {
			JOptionPane.showMessageDialog(this, t.toString());
		}

	};


	/**
	 * JListで選択したもののフィールドのオブジェクトをDialogに渡す。
	 */
	private final ActionListener SET_FIELD = (e) -> {
		int selectedIndex = fieldJList.getSelectedIndex();
		Object obj = fieldObjectList.get(selectedIndex);

		ModifyFieldDialog dialog = new ModifyFieldDialog(object, selectedIndex, this);
		dialog.setVisible(true);

	};


	/**
	 * Create the frame.
	 */
	InvokeUIFrame(int objectPoolIndex) {
		setLayouts();
		setListeners();
		this.objectPoolIndex = objectPoolIndex;
		init();
	}

	InvokeUIFrame(Object object) throws Throwable {
		setLayouts();
		setListeners();
		initForTest(object);
		this.objectPoolIndex = 0;
	}

	public static void main(String[] args) throws Throwable {
		InvokeUIFrame frame = new InvokeUIFrame("sato");
		frame.setVisible(true);
	}

	private void setLayouts() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(Consts.INVOKE_FRAME_SIZE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 0, 0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel upperleft_panel = new JPanel();
		panel.add(upperleft_panel);
		upperleft_panel.setLayout(new BorderLayout(0, 0));

		JPanel methodSearch_panel = new JPanel();
		upperleft_panel.add(methodSearch_panel, BorderLayout.NORTH);
		methodSearch_panel.setLayout(new GridLayout(0, 2, 0, 0));

		textField = new JTextField();
		methodSearch_panel.add(textField);
		textField.setColumns(10);

		JButton btnMethodSearch = new JButton("Find Method");
		methodSearch_panel.add(btnMethodSearch);

		JScrollPane methodScrollPane = new JScrollPane();
		upperleft_panel.add(methodScrollPane);

		methodJList = new JList(mothodsModel);
		methodScrollPane.setViewportView(methodJList);

		JPanel methodView = new JPanel();
		panel.add(methodView);
		methodView.setLayout(new BorderLayout(0, 0));

		btnInvoke = new JButton("invoke");

		methodView.add(btnInvoke, BorderLayout.SOUTH);

		methodParamListPanel = new JPanel();
		layout = new GridLayout();
		layout.setColumns(2);
		methodParamListPanel.setLayout(layout);
		methodView.add(methodParamListPanel, BorderLayout.CENTER);

		JPanel filedPanel = new JPanel();
		contentPane.add(filedPanel);
		filedPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblFields = new JLabel("Fields");
		filedPanel.add(lblFields, BorderLayout.NORTH);

		fieldJList = new JList(fieldModel);
		JScrollPane filedComp = new JScrollPane(fieldJList);
		filedPanel.add(filedComp, BorderLayout.CENTER);
//		filedComp.setLayout(new BorderLayout(0, 0));


		//filedComp.add(filedJList);

		JPanel setFiled_panel = new JPanel();
		filedPanel.add(setFiled_panel, BorderLayout.SOUTH);

		JButton btnSetField = new JButton("Set Field");
		setFiled_panel.add(btnSetField);
	}

	private void setListeners() {
		methodJList.addListSelectionListener(GET_METHOD_PARAM);
		btnInvoke.addActionListener(INVOKE);
		btnSetField.addActionListener(SET_FIELD);
	}

	/**
	 * 初期で渡されたオブジェクトのメソッド一覧をｊListに表示
	 */
	private void init() {
		object = ObjectPool.getInstance().get(objectPoolIndex);
		methods = object.getClass().getMethods();
		fields = object.getClass().getDeclaredFields();

		for (Method m : methods) {
			mothodsModel.addElement(m.toGenericString());
		}
		for (Field f : fields) {
			try {
				Object o = ReflectUtil.getField(object, f);
				fieldObjectList.add(o);
				fieldModel.addElement(f.toGenericString() + " = " + o);
			} catch (Throwable throwable) {
				throwable.printStackTrace();
				JOptionPane.showMessageDialog(this, throwable.getMessage());
			}

		}

	}

	private void initForTest(Object obj) throws Throwable {
		object = obj;
		methods = object.getClass().getMethods();
		fields = object.getClass().getDeclaredFields();
		for (Method m : methods) {
			mothodsModel.addElement(m.toGenericString());
		}
		for (Field f : fields) {
			Object o = ReflectUtil.getField(obj, f);
			fieldObjectList.add(o);
			fieldModel.addElement(f.toGenericString() + " = " + o);
		}
	}

	/**
	 * パラメータリストを消す.
	 * すでにパネルに何もコンポーネントがない場合何も起こらない。
	 * TODO util 移植
	 *
	 * @param jPanel
	 */
	private void deleteComponentFromPanel(JPanel jPanel) {
		inputParams.clear();
		Component[] comps = jPanel.getComponents();
		for (Component comp : comps) {
			jPanel.remove(comp);
		}
		jPanel.revalidate();
		jPanel.repaint();
	}

	/**
	 * paramパネルにコンポーネントを追加します。
	 * TODO utilに移植
	 *
	 * @param jPanel
	 * @param methodNames
	 * @return
	 */
	private void setCompToParamPanel(JPanel jPanel, List<String> methodNames) {
		inputParams.clear();
		System.out.println(methodNames);
		if (methodNames == null) {
			return;
		}
		if (methodNames.isEmpty()) {
			return;
		}
		layout.setRows(methodNames.size());
		for (int i = 0; i < methodNames.size(); i++) {
			jPanel.add(new JLabel(methodNames.get(i)));
			inputParams.add(new JTextField());
			jPanel.add(inputParams.get(i));
		}

	}

	public DefaultListModel<String> getFieldModel() {
		return fieldModel;
	}

	/**
	 * @param obj                変更されるオブジェクト
	 * @param fieldSelectedIndex オブジェクトが持つgetDeclaredFields()の中でユーザが選択したindex
	 */
	public void updateFieldsAt(Object obj, int fieldSelectedIndex) {
		object = obj;
		ObjectPool.getInstance().setObject(objectPoolIndex, obj);
		fieldObjectList.set(fieldSelectedIndex, obj);
		fieldModel.set(fieldSelectedIndex, fields[fieldSelectedIndex].toGenericString() + " = " + obj);
	}
}

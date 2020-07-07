package interpret.ui;

import interpret.Sample;
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
 * TODO メソッドの検索 listmodelの更新でArrayIndexOutOfBoundsExceptionが発生
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
	private JButton btnMethodSearch;

	private List<Integer> indexList = new ArrayList<>();
	/**
	 *
	 */
	private final ListSelectionListener GET_METHOD_PARAM = e -> {
		if (e.getValueIsAdjusting()) {
			return;
		}

        try {
            int index;
            if (indexList.isEmpty()) {
                index = methodJList.getSelectedIndex();
            } else {
                index = indexList.get(methodJList.getSelectedIndex());
            }

            Method met = methods[index];
			paramList = LexicalAnalyzer.findParams(met);
            deleteComponentFromPanel(methodParamListPanel);
            setCompToParamPanel(methodParamListPanel, paramList);
        } catch (IndexOutOfBoundsException ignore) {

        }


    };
	private final ActionListener INVOKE = (e) -> {
		try {
			int index;
			if (indexList.isEmpty()) {
				index = methodJList.getSelectedIndex();
			} else {
				index = indexList.get(methodJList.getSelectedIndex());
			}
			Object returnValue = ReflectUtil.invoke(object, methods[index],
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
		ModifyFieldDialog dialog = new ModifyFieldDialog(object, selectedIndex, this);
		dialog.setVisible(true);

	};

	/**
	 * メソッドの検索
	 */
	private final ActionListener FIND_METHOD = (e) -> {
		indexList = findMethod(textField.getText());


        if (!indexList.isEmpty()) {
            DefaultListModel<String> model = new DefaultListModel<>();

            for (int index : indexList) {
                model.addElement(methods[index].toGenericString());
			}
            methodJList.setModel(model);

        } else {
            methodJList.setModel(mothodsModel);
        }

    };


	/**
	 * Create the frame.
	 */
	InvokeUIFrame(int objectPoolIndex) {
		init();
		setLayouts();
		setListeners();
		this.objectPoolIndex = objectPoolIndex;
	}

	InvokeUIFrame(Object object) throws Throwable {
		setLayouts();
		setListeners();
		initForTest(object);
		this.objectPoolIndex = 0;
	}

	public static void main(String[] args) throws Throwable {
		Sample sample = new Sample();
		ObjectPool.getInstance().add(sample);
		InvokeUIFrame frame = new InvokeUIFrame(sample);
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

		btnMethodSearch = new JButton("Find Method");
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

		btnSetField = new JButton("Set Field");
		setFiled_panel.add(btnSetField);
	}

	private void setListeners() {
		btnMethodSearch.addActionListener(FIND_METHOD);
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
				JOptionPane.showMessageDialog(this, throwable.toString());
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


	/**
	 * @param obj                変更されるオブジェクト
	 * @param fieldSelectedIndex オブジェクトが持つgetDeclaredFields()の中でユーザが選択したindex
	 */
	public void updateFieldsAt(Object obj, int fieldSelectedIndex) {
		object = obj;
		ObjectPool.getInstance().setObject(objectPoolIndex, obj);
		try {

			System.out.println("updateFieldsAt invoke :" + ObjectPool.getInstance().getDisplayName(fields[fieldSelectedIndex]));

			Object updateObj = ReflectUtil.getField(obj, fields[fieldSelectedIndex]);
			System.out.println("update Fld obj : " + updateObj);
			fieldObjectList.set(fieldSelectedIndex, updateObj);
			fieldModel.set(fieldSelectedIndex, fields[fieldSelectedIndex].toGenericString() + " = " + updateObj);
		} catch (Throwable throwable) {
			JOptionPane.showMessageDialog(this, throwable.getMessage());
		}

	}

	/**
	 * @param key
	 * @return
	 */
	private List<Integer> findMethod(String key) {
		List<Integer> list = new ArrayList<>();
		if (key.isEmpty()) {
			return list;
		}

		for (int i = 0; i < methods.length; i++) {
			Method m = methods[i];
			if (m.toGenericString().contains(key)) {
				list.add(i);
			}
		}

		return list;
	}

}

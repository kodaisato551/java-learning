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

class InvokeUIFrame extends JFrame {

    private static final long serialVersionUID = -5188095995143316568L;
    private JPanel contentPane;

    private final int objectPoolIndex;//オブジェクトが指し示す

    private final DefaultListModel mothodsModel = new DefaultListModel();
    private final DefaultListModel fieldModel = new DefaultListModel();
    private JList methodJList;
    private final List<JTextField> inputParams = new ArrayList<>();
    private GridLayout layout;
    private JPanel methodParamListPanel;
    private JButton btnInvoke;
    private JList filedJList;

    private Object object;
    private Method[] methods;
    private Field[] fields;
    private List<String> paramList;

    /**
     * Create the frame.
     */
    InvokeUIFrame(int objectPoolIndex) {
		setLayouts();
		setListeners();
        this.objectPoolIndex = objectPoolIndex;
		init();

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

        JScrollPane methodScrollPane = new JScrollPane();
        panel.add(methodScrollPane);

		methodJList = new JList(mothodsModel);
        methodScrollPane.setViewportView(methodJList);

        JPanel methodView = new JPanel();
        panel.add(methodView);
        methodView.setLayout(new BorderLayout(0, 0));

		btnInvoke = new JButton("invoke");
		btnInvoke.addActionListener(INVOKE);
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

        JPanel filedComp = new JPanel();
        filedPanel.add(filedComp, BorderLayout.CENTER);
        filedComp.setLayout(new BorderLayout(0, 0));

		filedJList = new JList(fieldModel);
        filedComp.add(filedJList);
    }

    private void setListeners() {
		methodJList.addListSelectionListener(GET_METHOD_PARAM);
    }

    /**
     * 初期で渡されたオブジェクトのメソッド一覧をｊListに表示
     */
    private void init() {
		object = ObjectPool.getInstance().get(objectPoolIndex);
		methods = object.getClass().getMethods();
		fields = object.getClass().getFields();
        for (Method m : methods) {
			mothodsModel.addElement(m.toGenericString());
        }
        for (Field f : fields) {
			fieldModel.addElement(f.toGenericString());
        }

    }

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

    private final ActionListener INVOKE = (e) -> {
        try {
            Object returnValue = ReflectUtil.invoke(object, methods[methodJList.getSelectedIndex()],
                    LexicalAnalyzer.parse(paramList, inputParams));
            if (returnValue == null) {
                JOptionPane.showMessageDialog(this, "void");
            } else {
                JOptionPane.showMessageDialog(this, String.valueOf(returnValue));
            }
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(this, t.toString());
        }

    };
}
